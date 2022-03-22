package com.humlib.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

internal class UserTest {
    @Test
    fun canInstantiate() {
        val user = User(UUID.randomUUID())
        assertNotNull(user)
    }
}