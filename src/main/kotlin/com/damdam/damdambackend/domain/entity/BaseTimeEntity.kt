package com.damdam.damdambackend.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity {

    @CreatedDate
    @Column(columnDefinition = "DATETIME", updatable = false)
    lateinit var createdAt: LocalDateTime

    @LastModifiedDate
    @Column(columnDefinition = "DATETIME")
    lateinit var updatedAt: LocalDateTime
}
