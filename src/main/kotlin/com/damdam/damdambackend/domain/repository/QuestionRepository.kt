package com.damdam.damdambackend.domain.repository

import com.damdam.damdambackend.domain.entity.Question
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository : JpaRepository<Question, Int>
