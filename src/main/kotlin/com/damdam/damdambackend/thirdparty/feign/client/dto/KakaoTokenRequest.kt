package com.damdam.damdambackend.thirdparty.feign.client.dto

data class KakaoTokenRequest(
    val grantType: String = "authorization_code",
    val responseType: String = "code",
    val clientId: String,
    val redirectUri: String = "redirect_url",
    val code: String,
    val clientSecret: String,
)
