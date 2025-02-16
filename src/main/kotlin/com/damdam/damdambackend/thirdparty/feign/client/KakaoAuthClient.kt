package com.damdam.damdambackend.thirdparty.feign.client

import com.damdam.damdambackend.thirdparty.feign.client.dto.KakaoTokenResponse
import feign.Headers
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "kakaoAuthClient", url = "https://kauth.kakao.com")
interface KakaoAuthClient {

    @PostMapping("/oauth/token")
    @Headers("Content-Type: application/x-www-form-urlencoded;charset=utf-8")
    fun getAccessToken(
        @RequestParam("grant_type") grantType: String = "authorization_code",
        @RequestParam("client_id") clientId: String,
        @RequestParam("redirect_uri") redirectUri: String = "redirectUrl",
        @RequestParam("code") code: String,
        @RequestParam("client_secret") clientSecret: String
    ): KakaoTokenResponse
}
