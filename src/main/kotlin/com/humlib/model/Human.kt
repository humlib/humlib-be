package com.humlib.model

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import org.springframework.data.elasticsearch.annotations.Setting
import java.util.*

@Document(indexName = "humlib-human")
@Setting(settingPath = "elasticsearch_mappings/tags_analyzer.json")
data class Human(
    @Id
    val id: UUID?,

    @Field(type = FieldType.Text, analyzer = "autocomplete", searchAnalyzer = "standard")
    val tags: List<String>,

    @Field(type = FieldType.Keyword)
    val profession: String,

    @Field(type = FieldType.Text)
    val teaserText: String,

    @Field(type = FieldType.Text)
    val contentText: String,
)