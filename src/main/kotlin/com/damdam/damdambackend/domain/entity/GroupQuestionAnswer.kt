package com.damdam.damdambackend.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "group_question_answer")
class GroupQuestionAnswer(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(columnDefinition = "INT")
    val questionId: Int,

    content: String = "",
    userId: Int? = null,
    imageUrl: String = ""

) : BaseTimeEntity() {
    var content = content
        protected set

    var userId = userId
        protected set

    var imageUrl = imageUrl
        protected set
}
