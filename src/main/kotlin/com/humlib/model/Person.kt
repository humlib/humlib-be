package com.humlib.model

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName = "humlib-person")
data class Person(
    @Id
    val id: String? = null,

    var name: String
)