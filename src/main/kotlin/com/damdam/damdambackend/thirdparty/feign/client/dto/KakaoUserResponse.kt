package com.damdam.damdambackend.thirdparty.feign.client.dto

data class KakaoUserResponse(
    val properties: Properties,
)

data class Properties(
    val nickname: String,
)
