package com.humlib.mockdata

import com.humlib.model.Human
import com.humlib.model.Tags
import com.humlib.service.HumansService
import org.springframework.stereotype.Service
import java.util.*

@Service
class ElasticSearchData(val humansService: HumansService) {

    fun init() {
        val humanId : UUID = UUID.fromString("627cd437-2a42-4bf5-baa6-02688bf94d61")
        humansService.saveAndUpdateHumanById(
            id = humanId,
            human = Human(
                id = humanId,
                tags = Tags(listOf("Android","Game Development")),
                profession = "Full Stack Developer",
                teaserText = "I am a cool person",
                contentText = "and I like coffee")
        )
    }
}
