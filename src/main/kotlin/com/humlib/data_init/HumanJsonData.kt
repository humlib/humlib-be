package com.humlib.data_init

data class HumanJsonData(
    val id: String,
    val tags: TagsJsonData,
    val profession: String,
    val teaserText: String,
    val contentText: String,
)