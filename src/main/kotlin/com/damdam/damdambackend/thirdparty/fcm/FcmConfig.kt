package com.damdam.damdambackend.thirdparty.fcm

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream
import java.util.*
import javax.annotation.PostConstruct

@Component
class FcmConfig(
    @Value("\${fcm.value}")
    private val value: String,
) {
    @PostConstruct
    fun init() {
        try {
            if (FirebaseApp.getApps().isEmpty()) {
                val decodedJson = String(Base64.getDecoder().decode(value))
                FirebaseApp.initializeApp(
                    FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(ByteArrayInputStream(decodedJson.toByteArray())))
                        .build()
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
