package com.damdam.damdambackend.thirdparty.feign.client

import com.damdam.damdambackend.thirdparty.feign.client.dto.KakaoUserResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "kakaoUserClient", url = "https://kapi.kakao.com")
interface KakaoUserClient {

    @GetMapping("/v2/user/me")
    fun getUserInfo(@RequestHeader("Authorization") accessToken: String): KakaoUserResponse
}
