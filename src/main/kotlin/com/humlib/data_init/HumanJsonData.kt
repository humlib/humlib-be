package com.humlib.data_init

data class HumanJsonData(
    val id: String,
    val tags: List<String>,
    val profession: String,
    val teaserText: String,
    val contentText: String,
) {
    constructor() : this("", listOf(), "", "", "")
}