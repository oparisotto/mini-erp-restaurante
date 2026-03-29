package com.restaurante.api.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey

@Service
class JwtService {

    private val key: SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256)

    fun gerarToken(email: String, role: String): String {
        return Jwts.builder()
            .setSubject(email)
            .claim("role", role)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60))
            .signWith(key)
            .compact()
    }

    fun validarToken(token: String): String? {
        return try {
            val claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)

            claims.body.subject
        } catch (e: Exception){
            null
        }
    }

    fun getRole(token: String): String? {
        return try {
            val claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)

            claims.body["role"] as String
        } catch (e: Exception){
            null
        }
    }
}