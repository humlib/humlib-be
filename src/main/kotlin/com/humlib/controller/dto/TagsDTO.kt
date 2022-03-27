package com.humlib.controller.dto

import com.humlib.model.Tags

data class TagsDTO(
    val tags: List<String>
) {
    fun entity() = Tags(tags = tags)
}