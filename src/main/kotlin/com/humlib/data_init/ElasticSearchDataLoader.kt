package com.humlib.data_init

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.type.CollectionType
import com.fasterxml.jackson.databind.type.TypeFactory
import com.humlib.model.Human
import com.humlib.model.Tags
import com.humlib.service.HumansService
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import java.util.*


@Service
class ElasticSearchDataLoader(
    private val humansService: HumansService,
    @Value("classpath:data/humans.json")
    private val humansJsonFile: Resource
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        if (this.isInitialized()) {
            return
        }

        val humans: List<Human> = this.loadUsersFromFile()
        for (human in humans) {
            humansService.saveAndUpdateHumanById(human.id!!, human)
        }
    }

    private fun loadUsersFromFile(): List<Human> {
        val objectMapper = ObjectMapper()
        val collectionType: CollectionType = TypeFactory.defaultInstance().constructCollectionType(
            MutableList::class.java,
            HumanJsonData::class.java
        )
        val fakeHumans: List<HumanJsonData> = objectMapper.readValue(this.humansJsonFile.file, collectionType)
        return fakeHumans.stream().map(this::from).toList()
    }

    private fun from(humanJson: HumanJsonData): Human {
        return Human(
            UUID.fromString(humanJson.id),
            Tags(humanJson.tags.tags),
            humanJson.contentText,
            humanJson.profession,
            humanJson.teaserText
        )
    }

    private fun isInitialized(): Boolean {
        return this.humansService.count() > 0
    }

}