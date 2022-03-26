package com.humlib.model

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.util.*

@Document(indexName = "humlib-human")
data class Human(
    @Id
    val id: UUID?,

    @Field(type = FieldType.Nested)
    val tags: Tags,

    @Field(type = FieldType.Keyword)
    val profession: String,

    @Field(type = FieldType.Text)
    val teaserText: String,

    @Field(type = FieldType.Text)
    val contentText: String,
)