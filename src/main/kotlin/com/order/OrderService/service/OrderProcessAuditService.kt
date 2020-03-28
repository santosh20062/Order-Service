package com.order.OrderService.service

import com.order.OrderService.dao.OrderAuditRepository
import com.order.OrderService.model.OrderAudit
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service("orderProcessAuditService")
class OrderProcessAuditService(@Autowired val orderAuditRepository: OrderAuditRepository) {


    fun createOrderAudit(orderAudit :  OrderAudit): Mono<OrderAudit> {

        return orderAuditRepository.save(orderAudit)
    }

    fun findAllOrderAudit(): Flux<OrderAudit> {
        return orderAuditRepository.findAll()
    }

    fun getById(id: String): Mono<ResponseEntity<OrderAudit>> {
        return orderAuditRepository.findById(id)
                .map({ request -> ResponseEntity.ok<OrderAudit>(request) })
                .defaultIfEmpty(ResponseEntity.notFound().build<OrderAudit>())
    }


    fun getByOrderId(orderId: String): Flux<OrderAudit> {
        return orderAuditRepository.findByOrderId(orderId)
    }
}