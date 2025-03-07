package com.damdam.damdambackend.domain.repository

import com.damdam.damdambackend.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {
    fun findByName(name: String): User?
    fun findByEmail(email: String): User?
}
