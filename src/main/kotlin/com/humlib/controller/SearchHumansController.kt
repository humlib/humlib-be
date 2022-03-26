package com.humlib.controller

import com.humlib.model.Human
import com.humlib.model.HumansTags
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
    fun getAll(): List<Human> {
        return humanProfileRepository.findAll().toList()
    }

    @GetMapping("/contains_all")
    fun containsAll(
        @RequestParam pageNo: Int,
        @RequestParam pageSize: Int,
        @RequestBody humansTags: HumansTags
    ): List<Human> {
        val paging: Pageable = PageRequest.of(pageNo, pageSize)
        val pagedResult = humanProfileRepository.containsAtLeastNumberOfGivenTags(
            humansTags.tags,
            humansTags.tags.size,
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
        @RequestBody humansTags: HumansTags
    ): List<Human> {
        val paging: Pageable = PageRequest.of(pageNo, pageSize)
        val pagedResult = humanProfileRepository.containsAtLeastNumberOfGivenTags(
            humansTags.tags,
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