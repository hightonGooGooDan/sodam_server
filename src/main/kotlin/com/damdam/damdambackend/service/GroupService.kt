package com.damdam.damdambackend.service

import com.damdam.damdambackend.controller.dto.CreateGroupResponse
import com.damdam.damdambackend.controller.dto.GroupHolidaysResponse
import com.damdam.damdambackend.controller.dto.GroupHolidaysResponse.GroupHolidayElement
import com.damdam.damdambackend.domain.entity.Group
import com.damdam.damdambackend.domain.entity.GroupHoliday
import com.damdam.damdambackend.domain.entity.GroupUser
import com.damdam.damdambackend.domain.repository.GroupHolidayRepository
import com.damdam.damdambackend.domain.repository.GroupRepository
import com.damdam.damdambackend.domain.repository.GroupUserRepository
import com.damdam.damdambackend.domain.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.*

@Service
class GroupService(
    private val groupRepository: GroupRepository,
    private val userRepository: UserRepository,
    private val groupUserRepository: GroupUserRepository,
    private val groupHolidayRepository: GroupHolidayRepository,
) {

    fun createGroup(userId: Int, name: String, joinFamilyDate: LocalDate): CreateGroupResponse {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw IllegalStateException("User not found")

        val group = groupRepository.save(
            Group(
                headUserId = user.id,
                name = name,
                entryCode = UUID.randomUUID().toString().substring(28),
                point = 0,
                joinFamilyDate = joinFamilyDate,
            )
        )

        return CreateGroupResponse(group.entryCode)
    }

    fun createGroupHoliday(userId: Int, date: LocalDate) {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw IllegalStateException("User not found")
        val groupUser = groupUserRepository.findByUserId(user.id)

        groupHolidayRepository.save(
            GroupHoliday(
                groupId = groupUser.groupId,
                date = date,
            )
        )
    }

    @Transactional
    fun updateGroupHoliday(userId: Int, groupHolidayId: Int, newDate: LocalDate) {
        val groupHoliday = groupHolidayRepository.findByIdOrNull(groupHolidayId)
            ?: throw IllegalStateException("Group holiday not found")

        groupHoliday.updateDate(newDate)
    }

    @Transactional
    fun deleteGroupHoliday(userId: Int, groupHolidayId: Int) {
        val groupHoliday = groupHolidayRepository.findByIdOrNull(groupHolidayId)
            ?: throw IllegalStateException("Group holiday not found")

        groupHolidayRepository.delete(groupHoliday)
    }

    fun getGroupHolidays(userId: Int, groupId: Int): GroupHolidaysResponse {
        val group = groupRepository.findByIdOrNull(groupId)
            ?: throw IllegalStateException("Group not found")

        val groupHolidays = groupHolidayRepository.findAll().map {
            GroupHolidayElement(
                id = it.id,
                date = it.date,
            )
        }

        return GroupHolidaysResponse(group.id, groupHolidays)
    }

    fun joinGroup(userId: Int, groupEntryCode: String) {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw IllegalStateException("User not found")

        val group = groupRepository.findByEntryCode(groupEntryCode)
            ?: throw IllegalStateException("Group not found")

        groupUserRepository.save(
            GroupUser(
                userId = user.id,
                groupId = group.id,
            )
        )
    }

    @Transactional
    fun disConnectGroup(userId: Int) {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw IllegalStateException("User not found")

        val groupUser = groupUserRepository.findByUserId(user.id)

        groupUserRepository.delete(groupUser)
    }
}
