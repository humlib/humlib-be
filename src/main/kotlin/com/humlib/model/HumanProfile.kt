package com.humlib.model

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

@Document(indexName = "humlib-humanprofile")
data class HumanProfile(
    @Id
    val humanId: String?,

    @Field(type = FieldType.Keyword)
    var tags: List<String>,

    @Field(type = FieldType.Keyword)
    var profession: String,

    @Field(type = FieldType.Text)
    var teaserText: String,

    @Field(type = FieldType.Text)
    var contentText: String,
)