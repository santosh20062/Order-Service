package com.order.OrderService.dao

import com.order.OrderService.model.Order
import com.order.OrderService.model.OrderAudit
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository("orderAuditRepository")
interface OrderAuditRepository : ReactiveCrudRepository<OrderAudit,String> {

    @Query("{orderId: ?0 }")
    fun findByOrderId(orderId: String ) : Flux<OrderAudit>
}