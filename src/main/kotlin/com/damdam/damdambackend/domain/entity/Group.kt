package com.damdam.damdambackend.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "group_family")
class Group(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(columnDefinition = "INT")
    val headUserId: Int,

    @Column(columnDefinition = "VARCHAR(64)")
    val name: String,

    @Column(columnDefinition = "CHAR(10)")
    val entryCode: String,

    @Column(columnDefinition = "INT")
    val point: Int = 0,

    @Column(columnDefinition = "DATE")
    val joinFamilyDate: LocalDate,
) : BaseTimeEntity()
