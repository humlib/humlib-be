package com.humlib.security.config

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity

@EnableGlobalMethodSecurity(
    prePostEnabled = true
)
class SecurityConfig
