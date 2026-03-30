package com.restaurante.api.exception

import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(
        ex: RuntimeException,
        request: HttpServletRequest
    ): ResponseEntity<Any> {

        val erro = mapOf(
            "timestamp" to LocalDateTime.now(),
            "status" to 400,
            "error" to ex.message ,
            "path" to request.requestURI
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro)
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrity(
        ex: DataIntegrityViolationException,
        request: HttpServletRequest
    ): ResponseEntity<Any> {

        val mensagem = if (ex.message?.contains("users_email_key") == true) {
            "Email já está em uso"
        } else {
            "Erro de integridade de dados"
        }

        val erro = mapOf(
            "timestamp" to LocalDateTime.now(),
            "status" to 400,
            "error" to mensagem,
            "path" to request.requestURI
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(
        ex: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<Any> {

        val mensagem = ex.bindingResult.fieldErrors
            .map { it.defaultMessage }
            .firstOrNull() ?: "Erro de validação"

        val erro = mapOf(
            "timestamp" to LocalDateTime.now(),
            "status" to 400,
            "error" to mensagem,
            "path" to request.requestURI
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro)
    }
}
