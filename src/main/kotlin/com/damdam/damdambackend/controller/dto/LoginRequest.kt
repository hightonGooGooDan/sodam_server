package com.damdam.damdambackend.controller.dto

data class LoginRequest(
    val code: String,
    val deviceToken: String,
)
