package com.humlib.controller

import com.humlib.model.Human
import com.humlib.repository.HumanProfileRepository
import com.humlib.security.annotations.IsHumanWithSameId
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/humans/{id}")
@IsHumanWithSameId
class HumansController(
    val humanProfileRepository: HumanProfileRepository
) {

    @GetMapping
    fun getHuman(@PathVariable id: UUID, authentication: Authentication): Human {
        return humanProfileRepository.findById(id).get()
    }

    @PostMapping
    fun saveHuman(
        @PathVariable id: UUID,
        @RequestBody human: Human,
        authentication: Authentication,
    ): Human {
        val newHumanProfile = human.copy(id = id)
        humanProfileRepository.save(newHumanProfile)
        return newHumanProfile
    }

    @DeleteMapping
    fun deleteHuman(@PathVariable id: UUID, authentication: Authentication) {
        humanProfileRepository.deleteById(id)
    }
}