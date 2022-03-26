package com.humlib.service

import com.humlib.model.Human
import com.humlib.repository.HumanProfileRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class HumansService(
    private val humanProfileRepository: HumanProfileRepository
) {

    fun findHumanById(id: UUID): Human {
        return humanProfileRepository.findById(id).get()
    }

    fun saveAndUpdateHumanById(id: UUID, human: Human): Human {
        val newHumanProfile = human.copy(id = id)
        humanProfileRepository.save(newHumanProfile)
        return newHumanProfile
    }

    fun deleteHumanById(id: UUID) {
        humanProfileRepository.deleteById(id)
    }

}