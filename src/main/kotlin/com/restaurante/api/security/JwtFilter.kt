package com.restaurante.api.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.stereotype.Component

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

            if (email != null){

                val auth = UsernamePasswordAuthenticationToken(
                    email,
                    null,
                    emptyList()
                )
                SecurityContextHolder.getContext().authentication = auth
            }
        }

        filterChain.doFilter(request, response)
    }
}