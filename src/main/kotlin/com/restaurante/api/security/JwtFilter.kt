package com.restaurante.api.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.stereotype.Component
import org.springframework.security.core.authority.SimpleGrantedAuthority

@Component
class JwtFilter(
    private val jwtService: JwtService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ){

        val header = request.getHeader("Authorization")

        if (header != null && header.startsWith("Bearer ")){
            val token = header.replace("Bearer ", "")

            val email = jwtService.validarToken(token)
            val role = jwtService.getRole(token)

            if (email != null && role != null) {

                val auth = UsernamePasswordAuthenticationToken(
                    email,
                    null,
                    listOf(SimpleGrantedAuthority("ROLE_$role"))
                )
                SecurityContextHolder.getContext().authentication = auth
            }
        }
        filterChain.doFilter(request, response)
    }
}