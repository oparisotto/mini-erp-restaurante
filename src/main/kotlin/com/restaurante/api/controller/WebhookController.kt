package com.restaurante.api.controller

import com.restaurante.api.service.WebhookService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/webhooks")
class WebhookController(
    private val webhookService: WebhookService
){
    private val logger = LoggerFactory.getLogger(WebhookController::class.java)

    @PostMapping("/mercadopago")
    fun receber(@RequestBody body: Map<String, Any>): ResponseEntity<Void>{
        logger.info("Webhook recebido: {}", body)
        webhookService.processar(body)
        return ResponseEntity.ok().build()
    }
}