package com.damdam.damdambackend.controller.dto

import java.time.LocalDate

data class GroupDetailResponse(
    val groupId: Int,
    val groupName: String,
    val names: List<String>,
    val familyDate: LocalDate,
    val question: QuestionResponse,
    val point: Int,
) {
    data class QuestionResponse(
        val questionId: Int,
        val content: String,
    )
}
