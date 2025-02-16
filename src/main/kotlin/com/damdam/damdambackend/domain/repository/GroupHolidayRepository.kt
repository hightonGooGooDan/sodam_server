package com.damdam.damdambackend.domain.repository

import com.damdam.damdambackend.domain.entity.GroupHoliday
import org.springframework.data.jpa.repository.JpaRepository

interface GroupHolidayRepository : JpaRepository<GroupHoliday, Int>
