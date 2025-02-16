package com.damdam.damdambackend.service

import com.damdam.damdambackend.controller.dto.LoginResponse
import com.damdam.damdambackend.domain.entity.User
import com.damdam.damdambackend.domain.repository.UserRepository
import com.damdam.damdambackend.thirdparty.feign.client.KakaoAuthClient
import com.damdam.damdambackend.thirdparty.feign.client.KakaoUserClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val kakaoAuthClient: KakaoAuthClient,
    private val kakaoUserClient: KakaoUserClient,
    @Value("\${spring.security.oauth2.client.registration.kakao.client-id}")
    private val clientId: String,
    @Value("\${spring.security.oauth2.client.registration.kakao.client-secret}")
    private val clientSecret: String,
) {

    fun kakaoAuth(code: String, deviceToken: String): LoginResponse {
        val tokenResponse = kakaoAuthClient.getAccessToken(
            clientId = clientId,
            code = code,
            clientSecret = clientSecret,
        )

        val kakaoUserResponse = kakaoUserClient.getUserInfo("Bearer " + tokenResponse.accessToken)

        val user = userRepository.findByName(kakaoUserResponse.properties.nickname)

        if (user != null) {
            return LoginResponse(user.id, false)
        }

        val newUser = userRepository.save(
            User(
                email = "damdam@gmail.com",
                name = kakaoUserResponse.properties.nickname,
                deviceToken = deviceToken,
            )
        )

        return LoginResponse(newUser.id, true)
    }

    @Transactional
    fun updateName(userId: Int, name: String) {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw IllegalStateException("User with id $userId does not exist")

        user.updateName(name)
    }

    @Transactional
    fun withdraw(userId: Int) {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw IllegalStateException("User with id $userId does not exist")

        userRepository.delete(user)
    }
}
