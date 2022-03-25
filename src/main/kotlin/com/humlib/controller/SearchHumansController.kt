package com.humlib.controller

import com.humlib.model.HumanProfile
import com.humlib.model.HumanProfileTags
import com.humlib.repository.HumanProfileRepository
import com.humlib.security.annotations.IsKid
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/search")
@IsKid
class SearchHumansController(
    val humanProfileRepository: HumanProfileRepository
) {

    @GetMapping
    fun getAll(): List<HumanProfile> {
        return humanProfileRepository.findAll().toList()
    }

    @GetMapping("/contains_all")
    fun containsAll(
        @RequestParam pageNo: Int,
        @RequestParam pageSize: Int,
        @RequestBody humanProfileTags: HumanProfileTags
    ): List<HumanProfile> {
        val paging: Pageable = PageRequest.of(pageNo, pageSize)
        val pagedResult = humanProfileRepository.containsAtLeastNumberOfGivenTags(
            humanProfileTags.tags,
            humanProfileTags.tags.size,
            paging
        )
        return if (pagedResult.hasContent()) {
            pagedResult.content
        } else {
            ArrayList()
        }
    }

    @GetMapping("/contains")
    fun contains(
        @RequestParam matchesAtLeast: Int?,
        @RequestParam pageNo: Int,
        @RequestParam pageSize: Int,
        @RequestBody humanProfileTags: HumanProfileTags
    ): List<HumanProfile> {
        val paging: Pageable = PageRequest.of(pageNo, pageSize)
        val pagedResult = humanProfileRepository.containsAtLeastNumberOfGivenTags(
            humanProfileTags.tags,
            matchesAtLeast ?: 1,
            paging
        )
        return if (pagedResult.hasContent()) {
            pagedResult.content
        } else {
            ArrayList()
        }
    }
}