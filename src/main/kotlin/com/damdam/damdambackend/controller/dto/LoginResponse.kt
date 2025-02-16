package com.damdam.damdambackend.controller.dto

data class LoginResponse(
    val userId: Int,
    val isFirstLogin: Boolean,
)
