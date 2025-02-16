package com.damdam.damdambackend.domain.repository

import com.damdam.damdambackend.domain.entity.Group
import org.springframework.data.jpa.repository.JpaRepository

interface GroupRepository : JpaRepository<Group, Int> {

    fun findByEntryCode(entryCode: String): Group?
}
