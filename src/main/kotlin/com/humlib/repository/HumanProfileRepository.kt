package com.humlib.repository

import com.humlib.model.HumanProfile
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.annotations.Query
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface HumanProfileRepository : ElasticsearchRepository<HumanProfile, String> {
    @Query("{\"bool\": {\"must\": {\"match_all\": {}}, \"filter\": {\"terms\": {\"tags\": ?0 }}}}")
    fun findByFilteredTagQuery(tags: List<String>, pageable: Pageable?): Page<HumanProfile>
}