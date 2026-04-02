package com.restaurante.api.service

import com.mercadopago.client.payment.PaymentClient
import com.restaurante.api.repository.PagamentoRepository
import com.restaurante.api.repository.PedidoRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class WebhookService(
    private val pagamentoRepository: PagamentoRepository,
    private val pedidoRepository: PedidoRepository,
){
    private val logger = LoggerFactory.getLogger(WebhookService::class.java)

    fun processar (body: Map<String, Any>) {
        try {

            val tipo = body["type"] as? String ?: return
            if (tipo != "payment") return

            val data = body["data"] as? Map<* , *> ?: return
            val paymentId = data["id"]?.toString() ?: return

            logger.info("Processando webhook de pagamento ID: {}", paymentId)

            // Busca detalhes do pagamento do Mercado Pago
            val client = PaymentClient()
            val payment = client.get(paymentId.toLong())

            logger.info("Status do pagamento {}: {}", paymentId, payment.status)

            // Busca o pagamento no banco pela referência do gateway
            val pagamento = pagamentoRepository.findByReferenciaGateway(paymentId)
                ?: run {
                    logger.warn("Pagamento não encontrado no banco para referência: {}", paymentId)
                    return
                }

            // Mapeia status
            val novoStatus = when (payment.status) {
                "approved"      -> "confirmado"
                "cancelled"     -> "cancelado"
                "rejected"      -> "cancelado"
                else            -> return
            }

            // Atualiza status de pagamento
            val pagamentoAtualizado = pagamento.copy(status = novoStatus)
            pagamentoRepository.save(pagamentoAtualizado)
            logger.info("Pagamento {} atualizado para {}", paymentId, novoStatus)

            // Se confirmado, atualiza status do pedido para "pago"
            if (novoStatus == "confirmado"){
                val pedido = pagamento.pedido ?: return
                pedido.status = "pago"
                pedidoRepository.save(pedido)
                logger.info("Pedido {} atualizado para pago", pedido.id)
            }

        } catch (e: Exception){
            logger.error("Erro ao processar webhook: {}", e.message, e)
        }
    }
}