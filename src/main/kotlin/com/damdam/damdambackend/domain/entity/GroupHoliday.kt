package com.damdam.damdambackend.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "group_holiday")
class GroupHoliday(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(columnDefinition = "INT")
    val groupId: Int,

    date: LocalDate,

    ) : BaseTimeEntity() {
    var date = date
        protected set

    fun updateDate(date: LocalDate) {
        this.date = date
    }
}
