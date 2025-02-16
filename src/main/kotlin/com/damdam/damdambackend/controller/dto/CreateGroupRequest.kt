package com.damdam.damdambackend.controller.dto

import java.time.LocalDate

data class CreateGroupRequest(
    val name: String,
    val joinFamilyDate: LocalDate,
)
