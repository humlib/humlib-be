package com.humlib.service

import com.humlib.model.Human
import com.humlib.model.Tags
import com.humlib.repository.HumansRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


@Service
class SearchHumansService(
    private val humansRepository: HumansRepository,
) {

    fun getAllHumans() = humansRepository.findAll().toList()

    fun search(keyword: String): List<Human?>? {
        return this.humansRepository.search(keyword)
    }

    /**
     * Pageable search request.
     * Return all @return[Human] whose profile matches to all given @param[tags]
     */
    fun searchContainsAllHumans(paging: Pageable, tags: Tags): List<Human> {
        val pagedResult = humansRepository.containsAtLeastNumberOfGivenTags(
            tags.tags, tags.tags.size, paging
        )
        return if (pagedResult.hasContent()) {
            pagedResult.content
        } else {
            ArrayList()
        }
    }

    /**
     * Pageable search request.
     * Return all @return[Human] whose profile matches at least a number (@param[matchesAtLeast]) of given @param[tags].
     * If the param @param[matchesAtLeast] is not given, profiles with at least one match will be searched.
     */
    fun searchContainsHumans(paging: Pageable, tags: Tags, matchesAtLeast: Int?): List<Human> {
        if (matchesAtLeast != null && matchesAtLeast <= 0) {
            throw IllegalArgumentException("""search param "matchesAtLeast" must be positive""")
        }
        val pagedResult = humansRepository.containsAtLeastNumberOfGivenTags(
            tags.tags, matchesAtLeast ?: 1, paging
        )
        return if (pagedResult.hasContent()) {
            pagedResult.content
        } else {
            ArrayList()
        }
    }
}