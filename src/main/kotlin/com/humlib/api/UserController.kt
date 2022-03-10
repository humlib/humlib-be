package com.humlib.api

import com.humlib.model.User
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class UserController {
    @GetMapping("/user/{id}")
    fun getUser(@PathVariable id: UUID): ResponseEntity<User> = ResponseEntity.ok(User(id = id))
}
