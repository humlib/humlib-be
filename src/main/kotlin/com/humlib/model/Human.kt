package com.humlib.model

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.*
import org.springframework.data.elasticsearch.core.suggest.Completion
import java.util.*

@Document(indexName = "humlib-human")
@Setting(settingPath = "elasticsearch_mappings/tags_analyzer.json")
data class Human(
    @Id
    val id: UUID?,

    @CompletionField
    val tags: Completion,

    @Field(type = FieldType.Keyword)
    val profession: String,

    @Field(type = FieldType.Text)
    val teaserText: String,

    @Field(type = FieldType.Text)
    val contentText: String,
)