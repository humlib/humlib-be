package com.humlib.service

import com.humlib.model.Human
import com.humlib.repository.HumansRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class HumansService(
    private val humansRepository: HumansRepository
) {
    fun findHumanById(id: UUID) = humansRepository.findById(id).get()

    fun saveAndUpdateHumanById(id: UUID, human: Human): Human {
        val newHumanProfile = human.copy(id = id)
        humansRepository.save(newHumanProfile)
        return newHumanProfile
    }

    fun deleteHumanById(id: UUID) = humansRepository.deleteById(id)
}