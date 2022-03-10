package com.humlib.api

import com.humlib.model.User
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class UserController {
    @GetMapping("/user/{id}")
    @PreAuthorize("hasAuthority('SCOPE_users')")
    fun getUser(@PathVariable id: UUID, authentication: Authentication): ResponseEntity<User> =
        ResponseEntity.ok(User(id = UUID.fromString(authentication.name)))
}
