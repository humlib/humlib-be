package com.humlib.controller

import com.humlib.model.HumanProfile
import com.humlib.model.HumanProfileTags
import com.humlib.repository.HumanProfileRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/profile")
class HumanProfileController(
    val humanProfileRepository: HumanProfileRepository
) {

    @GetMapping("/all")
    fun getAll(): List<HumanProfile> {
        return humanProfileRepository.findAll().toList()
    }

    @GetMapping
    fun getById(@RequestParam humanId: String): HumanProfile {
        return humanProfileRepository.findById(humanId).get()
        // TODO what happens if the human id cannot be found? -> send 404 response
    }

    @GetMapping("/search")
    fun findByTags(
        @RequestParam pageNo: Int,
        @RequestParam pageSize: Int,
        @RequestBody humanProfileTags: HumanProfileTags
    ): List<HumanProfile> {
        val paging: Pageable = PageRequest.of(pageNo, pageSize)
        val pagedResult = humanProfileRepository.findByFilteredTagQuery(humanProfileTags.tags, paging)
        return if (pagedResult.hasContent()) {
            pagedResult.content
        } else {
            ArrayList()
        }
    }

    @PostMapping
    fun saveById(@RequestParam humanId: String, @RequestBody humanProfile: HumanProfile): HumanProfile {
        val newHumanProfile = humanProfile.copy(humanId = humanId)
        humanProfileRepository.save(newHumanProfile)
        return newHumanProfile
    }

    @DeleteMapping
    fun deleteById(@RequestParam humanId: String) {
        humanProfileRepository.deleteById(humanId)
    }
}