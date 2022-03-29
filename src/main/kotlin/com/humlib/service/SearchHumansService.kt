package com.humlib.service

import com.humlib.model.Human
import com.humlib.model.Tags
import com.humlib.repository.HumansRepository
import org.elasticsearch.action.search.SearchRequest
import org.elasticsearch.action.search.SearchResponse
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.search.builder.SearchSourceBuilder
import org.elasticsearch.search.suggest.SuggestBuilder
import org.elasticsearch.search.suggest.SuggestBuilders
import org.elasticsearch.search.suggest.SuggestionBuilder
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


@Service
class SearchHumansService(
    private val humansRepository: HumansRepository,
    private val elasticsearchClient: RestHighLevelClient
) {

    fun getAllHumans() = humansRepository.findAll().toList()

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

    fun searchByTag(tag: String): SearchResponse {
        val searchSourceBuilder = SearchSourceBuilder()
        val termSuggestionBuilder: SuggestionBuilder<*> = SuggestBuilders.termSuggestion("tags.tags").text(tag)
        val suggestBuilder = SuggestBuilder()
        suggestBuilder.addSuggestion("suggest_tags", termSuggestionBuilder)
        searchSourceBuilder.suggest(suggestBuilder)
        val searchRequest = SearchRequest()
        searchRequest.indices("humlib-human")
        searchRequest.source(searchSourceBuilder)
        return elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT)
    }


}