package com.humlib.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "elasticsearch")
@ConstructorBinding
data class ElasticsearchConfig(
    val port: Int,
    val host: String,
    val username: String,
    val password: String,
    val scheme: String
)