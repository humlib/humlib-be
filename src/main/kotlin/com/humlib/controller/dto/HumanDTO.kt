package com.humlib.controller.dto

import com.humlib.model.Human
import com.humlib.model.Tags

data class HumanDTO(
    val tags: List<String>,
    val profession: String,
    val teaserText: String,
    val contentText: String
) {
    fun entity() = Human(
        id = null,
        tags = Tags(tags = tags),
        profession = profession,
        teaserText = teaserText,
        contentText = contentText,
    )

    companion object {
        fun from(human: Human) = HumanDTO(
            tags = human.tags.tags,
            profession = human.profession,
            teaserText = human.teaserText,
            contentText = human.contentText
        )
    }
}

fun List<Human>.toDtos() = this.map(HumanDTO.Companion::from)
