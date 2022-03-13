package com.humlib.repository

import com.humlib.model.Person
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface PersonRepository : ElasticsearchRepository<Person, String>