package com.humlib.service

import com.humlib.model.Human
import com.humlib.model.HumansTags
import com.humlib.repository.HumanProfileRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class SearchHumansService(
    private val humanProfileRepository: HumanProfileRepository
) {

    fun getAllHumans(): List<Human> {
        return humanProfileRepository.findAll().toList()
    }

    /**
     * Pageable search request.
     * Return all @return[Human] whose profile matches to all given @param[humansTags]
     */
    fun searchContainsAllHumans(paging: Pageable, humansTags: HumansTags): List<Human> {
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

    /**
     * Pageable search request.
     * Return all @return[Human] whose profile matches at least a number (@param[matchesAtLeast]) of given @param[humansTags].
     * If the param @param[matchesAtLeast] is not given, profiles with at least one match will be searched.
     */
    fun searchContainsHumans(paging: Pageable, humansTags: HumansTags, matchesAtLeast: Int?): List<Human> {
        if (matchesAtLeast != null && matchesAtLeast <= 0) {
            throw IllegalArgumentException("""search param "matchesAtLeast" must be positive""");
        }
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