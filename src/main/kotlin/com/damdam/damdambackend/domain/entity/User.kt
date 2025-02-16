package com.damdam.damdambackend.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "user")
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @field:NotNull
    @Column(columnDefinition = "VARCHAR(64)")
    val email: String,

    @field:NotNull
    @Column(columnDefinition = "VARCHAR(2000)")
    val deviceToken: String,

    name: String,
) : BaseTimeEntity() {
    @Column(columnDefinition = "VARCHAR(255)")
    var name = name
        protected set

    fun updateName(name: String) {
        this.name = name
    }
}
