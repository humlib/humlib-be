package com.humlib.controller

import com.humlib.model.HumanProfile
import com.humlib.model.HumanProfileTags
import com.humlib.repository.HumanProfileRepository
import com.humlib.security.annotations.IsHumanWithSameId
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/profile")
@IsHumanWithSameId
class HumanProfileController(
    val humanProfileRepository: HumanProfileRepository
) {

    @GetMapping("/all")
    fun getAll(@PathVariable id: UUID, authentication: Authentication): List<HumanProfile> {
        return humanProfileRepository.findAll().toList()
    }

    @GetMapping
    fun getById(@PathVariable id: UUID, authentication: Authentication): HumanProfile {
        return humanProfileRepository.findById(id).get()
    }

    @GetMapping("/search/contains_all")
    fun findByAllTagsForPage(
        @PathVariable id: UUID,
        authentication: Authentication,
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

    @GetMapping("/search/contains")
    fun findByAtLeastNumberOfTagsForPage(
        @PathVariable id: UUID,
        authentication: Authentication,
        @RequestParam atLeastNumberOfMatches: Int?,
        @RequestParam pageNo: Int,
        @RequestParam pageSize: Int,
        @RequestBody humanProfileTags: HumanProfileTags
    ): List<HumanProfile> {
        val paging: Pageable = PageRequest.of(pageNo, pageSize)
        val pagedResult = humanProfileRepository.containsAtLeastNumberOfGivenTags(
            humanProfileTags.tags,
            atLeastNumberOfMatches ?: 1,
            paging
        )
        return if (pagedResult.hasContent()) {
            pagedResult.content
        } else {
            ArrayList()
        }
    }

    @PostMapping("/{id}")
    fun saveById(
        @PathVariable id: UUID,
        authentication: Authentication,
        @RequestBody humanProfile: HumanProfile
    ): HumanProfile {
        val newHumanProfile = humanProfile.copy(id = id)
        humanProfileRepository.save(newHumanProfile)
        return newHumanProfile
    }

    @DeleteMapping
    fun deleteById(@PathVariable id: UUID, authentication: Authentication) {
        humanProfileRepository.deleteById(id)
    }
}