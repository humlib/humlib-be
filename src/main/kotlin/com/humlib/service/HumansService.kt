package com.humlib.service

import com.humlib.model.Human
import com.humlib.repository.HumansRepository
import org.keycloak.admin.client.Keycloak
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class HumansService(
    private val humansRepository: HumansRepository,
    private val keycloak: Keycloak,
    @Value("\${keycloak.realm}")
    private val realm: String
) {
    fun findHumanById(id: UUID) = humansRepository.findById(id).get()

    fun saveAndUpdateHumanById(id: UUID, human: Human): Human {
        val newHumanProfile = human.copy(id = id)
        humansRepository.save(newHumanProfile)
        return newHumanProfile
    }

    fun deleteHumanById(id: UUID) : Boolean {
        humansRepository.deleteById(id)
        keycloak
            .realm(realm)
            .users()
            .delete(id.toString())
        return true
    }
}
