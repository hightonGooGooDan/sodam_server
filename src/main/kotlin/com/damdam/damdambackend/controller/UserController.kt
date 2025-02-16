package com.damdam.damdambackend.controller

import com.damdam.damdambackend.controller.dto.LoginRequest
import com.damdam.damdambackend.controller.dto.UpdateUserInfoRequest
import com.damdam.damdambackend.service.UserService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/users")
@RestController
class UserController(
    private val userService: UserService,
) {

    @PostMapping("/login")
    fun kakaoAuth(@RequestBody loginRequest: LoginRequest) =
        userService.kakaoAuth(loginRequest.code, loginRequest.deviceToken)

    @PatchMapping
    fun updateUserInfo(
        @RequestHeader("user-identifier") userId: Int,
        @RequestBody updateUserInfoRequest: UpdateUserInfoRequest
    ) {
        userService.updateName(userId, updateUserInfoRequest.name)
    }

    @DeleteMapping
    fun deleteUserInfo(
        @RequestHeader("user-identifier") userId: Int,
    ) {
        userService.withdraw(userId)
    }
}
