package com.humlib.controller

import com.humlib.model.HumanProfile
import com.humlib.repository.HumanProfileRepository
import com.humlib.security.annotations.IsHumanWithSameId
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/profile/{id}")
@IsHumanWithSameId
class HumanProfileController(
    val humanProfileRepository: HumanProfileRepository
) {

    @GetMapping
    fun getById(@PathVariable id: UUID, authentication: Authentication): HumanProfile {
        return humanProfileRepository.findById(id).get()
    }

    @PostMapping
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