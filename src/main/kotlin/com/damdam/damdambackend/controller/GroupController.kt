package com.damdam.damdambackend.controller

import com.damdam.damdambackend.controller.dto.CreateGroupHolidayRequest
import com.damdam.damdambackend.controller.dto.CreateGroupRequest
import com.damdam.damdambackend.controller.dto.JoinGroupRequest
import com.damdam.damdambackend.controller.dto.UpdateHolidayRequest
import com.damdam.damdambackend.service.GroupService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/groups")
@RestController
class GroupController(
    private val groupService: GroupService,
) {

    @PostMapping
    fun createGroup(
        @RequestHeader("user-identifier") userId: Int,
        @RequestBody request: CreateGroupRequest,
    ) = groupService.createGroup(userId, request.name, request.joinFamilyDate)

    @PostMapping("/holiday")
    fun createGroupHoliday(
        @RequestHeader("user-identifier") userId: Int,
        @RequestBody request: CreateGroupHolidayRequest,
    ) {
        groupService.createGroupHoliday(userId, request.date)
    }

    @PostMapping("/join")
    fun joinGroup(
        @RequestHeader("user-identifier") userId: Int,
        @RequestBody request: JoinGroupRequest,
    ) {
        groupService.joinGroup(userId, request.groupEntryCode)
    }

    @DeleteMapping("/disconnect")
    fun disconnectGroup(@RequestHeader("user-identifier") userId: Int) {
        groupService.disConnectGroup(userId)
    }

    @PutMapping("/holiday/{holidayId}")
    fun updateHoliday(
        @RequestHeader("user-identifier") userId: Int,
        @PathVariable holidayId: Int,
        @RequestBody request: UpdateHolidayRequest,
    ) {
        groupService.updateGroupHoliday(userId, holidayId, request.date)
    }

    @DeleteMapping("/holiday/{holidayId}")
    fun deleteHoliday(
        @RequestHeader("user-identifier") userId: Int,
        @PathVariable holidayId: Int,
    ) {
        groupService.deleteGroupHoliday(userId, holidayId)
    }

    @GetMapping("/holidays")
    fun getGroupHolidays(
        @RequestHeader("user-identifier") userId: Int,
        @RequestParam("groupId") groupId: Int,
    ) = groupService.getGroupHolidays(userId, groupId)
}
