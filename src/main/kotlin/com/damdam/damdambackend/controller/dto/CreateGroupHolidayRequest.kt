package com.damdam.damdambackend.controller.dto

import java.time.LocalDate

data class CreateGroupHolidayRequest(
    val date: LocalDate,
)
