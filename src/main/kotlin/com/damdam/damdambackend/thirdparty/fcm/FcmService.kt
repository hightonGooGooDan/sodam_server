package com.damdam.damdambackend.thirdparty.fcm

import com.damdam.damdambackend.domain.entity.GroupQuestionAnswer
import com.damdam.damdambackend.domain.repository.GroupQuestionAnswerRepository
import com.damdam.damdambackend.domain.repository.QuestionRepository
import com.damdam.damdambackend.domain.repository.UserRepository
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.MulticastMessage
import com.google.firebase.messaging.Notification
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class FcmService(
    private val questionRepository: QuestionRepository,
    private val groupQuestionAnswerRepository: GroupQuestionAnswerRepository,
    private val userRepository: UserRepository,
) {

    fun sendQuestion() {
        val question = questionRepository.findByIdOrNull(Random.nextInt(20))
            ?: throw IllegalArgumentException("Question not found")

        val users = userRepository.findAll()
        val deviceTokens = users.map { it.deviceToken }

        val groupQuestionAnswers = users.map {
            GroupQuestionAnswer(
                questionId = question.id,
                userId = it.id,
            )
        }

        groupQuestionAnswerRepository.saveAll(groupQuestionAnswers)

        val messages = MulticastMessage.builder()
            .addAllTokens(deviceTokens)
            .setNotification(
                Notification.builder()
                    .setTitle(TITLE)
                    .setBody(CONTENT + question.content)
                    .build()
            )
            .build()

        FirebaseMessaging.getInstance().sendMulticastAsync(messages);
    }

    companion object {
        private const val TITLE = "질문이 도착했어요!"
        private const val CONTENT = "질문 내용 :"
    }
}
