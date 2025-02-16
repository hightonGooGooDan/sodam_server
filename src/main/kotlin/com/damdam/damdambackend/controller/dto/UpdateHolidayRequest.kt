package com.damdam.damdambackend.controller.dto

import java.time.LocalDate

data class UpdateHolidayRequest(
    val date: LocalDate,
)
