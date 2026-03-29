package com.restaurante.api.config

import com.restaurante.api.security.JwtFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(
    private val jwtFilter: JwtFilter
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/auth/**"
                    ).permitAll()

                    .requestMatchers("/users/**").hasRole("ADMIN")

                    .requestMatchers(
                        "/enderecos/**",
                        "/pedidos/**",
                        "/produtos/**",
                        "/categorias/**",
                        "/cupons/**",
                        "/taxas-entrega/**"
                    ).authenticated()

                    .anyRequest().authenticated()
            }
            .addFilterBefore(jwtFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}