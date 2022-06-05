package com.humlib.service

import org.elasticsearch.action.search.SearchRequest
import org.elasticsearch.common.lucene.search.function.CombineFunction
import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder
import java.util.*

class Suggester {

    fun getSuggestRequest(indexAlias: String, maxSuggestions: Int): SearchRequest {
        val searchRequest = SearchRequest(indexAlias)
        searchRequest.source().size(maxSuggestions)
        val highlightBuilder = HighlightBuilder()
        highlightBuilder.preTags("").postTags("</mark")
            .field("", 1000, 1)
            .field("", 1000, 1)
        searchRequest.source().highlighter(highlightBuilder)
        return searchRequest
    }

    fun getSuggestQueryBuilder(searchTerm: String, filterBuilder: QueryBuilder): QueryBuilder {
        val queryBuilder: QueryBuilder
        if (searchTerm.length < 3) {
            queryBuilder = getPrefixQueryBuilder(searchTerm)
        } else {
            queryBuilder = getPrefixQueryBuilder(searchTerm)
            //queryBuilder = null //queryBuilder with BoostedFields
        }
        return QueryBuilders.boolQuery().must(queryBuilder).must(filterBuilder)
    }

    fun getFieldBoosts(): Map<String, FieldBoost> {
        val queryBuilder = QueryBuilders.matchAllQuery()
        var searchRequest = SearchRequest("INDEX_TO_BOOST")
        searchRequest.source().size(30).query(queryBuilder) //MAX results

        val response = //RestHighLevelClient.search(searchRequest, RequetOptions.DEFAULT)

    }

    fun getSearchRequest(suggestParameter: SuggestParameter, indexAlias: String, filterBuilder: QueryBuilder): SearchRequest {
        var queryBuilder = getSuggestQueryBuilder(suggestParameter.searchTerm, filterBuilder)
        val suggestRequest = getSuggestRequest(indexAlias, suggestParameter.maxSuggestions)
        suggestRequest.source().query(queryBuilder)
        return suggestRequest
    }

    fun getPrefixQueryBuilder(searchTerm: String): QueryBuilder {
        var prefixQueryBuilder = QueryBuilders.prefixQuery("FIELD??", searchTerm)
        var scoreFunction = ScoreFunctionBuilders.fieldValueFactorFunction("FIELD_MIT_SCORE")
            .missing(1.0)
            .setWeight(0.0002f)

        return QueryBuilders.functionScoreQuery(prefixQueryBuilder, scoreFunction).boostMode(CombineFunction.SUM)
    }

}