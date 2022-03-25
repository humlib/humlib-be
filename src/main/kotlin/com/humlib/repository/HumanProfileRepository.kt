package com.humlib.repository

import com.humlib.model.HumanProfile
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.annotations.Query
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import java.util.*

interface HumanProfileRepository : ElasticsearchRepository<HumanProfile, UUID> {
    @Query("{\"terms_set\": {\"tags\": {\"terms\": ?0 , \"minimum_should_match_script\": {\"source\": \"?1\" }}}}")
    fun containsAtLeastNumberOfGivenTags(
        tags: List<String>,
        numberOfMatches: Int,
        pageable: Pageable?
    ): Page<HumanProfile>
}