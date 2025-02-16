package com.damdam.damdambackend.controller.dto

import java.time.LocalDate

data class GroupHolidaysResponse(
    val groupId: Int,
    val groupHolidays: List<GroupHolidayElement>,
) {
    data class GroupHolidayElement(
        val id: Int,
        val date: LocalDate,
    )
}
