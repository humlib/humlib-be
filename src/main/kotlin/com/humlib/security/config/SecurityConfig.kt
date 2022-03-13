package com.humlib.security.config

import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain

@EnableMethodSecurity
class SecurityConfig {

    @Bean
    @Order(1)
    fun actuator(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            securityMatcher("/actuator/health/**")
            authorizeRequests {
                authorize("/actuator/health", permitAll)
                authorize("/actuator/health/liveness", permitAll)
                authorize("/actuator/health/readiness", permitAll)
                authorize(anyRequest, denyAll)
            }
        }
        return http.build()
    }

    @Bean
    fun fallback(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            securityMatcher("/**")
            authorizeRequests {
                authorize(anyRequest, authenticated)
            }
            oauth2ResourceServer {
                jwt { }
            }
        }
        return http.build()
    }
}
