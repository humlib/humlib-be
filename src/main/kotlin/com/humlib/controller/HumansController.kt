package com.humlib.controller

import com.humlib.controller.dto.HumanDTO
import com.humlib.security.annotations.IsHumanWithSameId
import com.humlib.service.HumansService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/humans/{id}")
@IsHumanWithSameId
class HumansController(
    val humansService: HumansService
) {
    @GetMapping
    fun getHuman(@PathVariable id: UUID, authentication: Authentication) =
        HumanDTO.from(humansService.findHumanById(id))

    @PostMapping
    fun saveHuman(
        @PathVariable id: UUID,
        @RequestBody human: HumanDTO,
        authentication: Authentication,
    ) = HumanDTO.from(humansService.saveAndUpdateHumanById(id, human.entity()))

    @DeleteMapping
    fun deleteHuman(@PathVariable id: UUID, authentication: Authentication) = humansService.deleteHumanById(id)
}