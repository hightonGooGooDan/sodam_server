package com.damdam.damdambackend.domain.repository

import com.damdam.damdambackend.domain.entity.GroupQuestionAnswer
import org.springframework.data.jpa.repository.JpaRepository

interface GroupQuestionAnswerRepository : JpaRepository<GroupQuestionAnswer, Int>
