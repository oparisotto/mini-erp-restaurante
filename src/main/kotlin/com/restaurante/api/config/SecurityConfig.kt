package com.restaurante.api.config

import com.restaurante.api.security.JwtFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

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

                    // Rotas públicas
                    .requestMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/auth/**",
                        "/webhooks/**"
                    ).permitAll()

                    // Somente ADMIN
                    .requestMatchers("/users/**").hasRole("ADMIN")
                    .requestMatchers("/cupons/**").hasRole("ADMIN")
                    .requestMatchers("taxas_entrega/**").hasRole("ADMIN")
                    .requestMatchers("PUT", "/pedidos/*/status").hasRole("ADMIN")
                    .requestMatchers("GET", "/pedidos").hasRole("ADMIN")
                    .requestMatchers("/dashboard/**").hasRole("ADMIN")

                    // CLIENTE e ADMIN
                    .requestMatchers("/produtos/**").authenticated()
                    .requestMatchers("/categorias/**").authenticated()
                    .requestMatchers("/enderecos/**").authenticated()
                    .requestMatchers("/pagamentos/**").authenticated()
                    .requestMatchers("POST", "/pedidos").authenticated()

                    .anyRequest().authenticated()
            }
            .addFilterBefore(jwtFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}