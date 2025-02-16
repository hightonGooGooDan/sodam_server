package com.damdam.damdambackend.domain.repository

import com.damdam.damdambackend.domain.entity.GroupUser
import org.springframework.data.jpa.repository.JpaRepository

interface GroupUserRepository : JpaRepository<GroupUser, Long> {
    fun findByUserId(userId: Int): GroupUser
}
