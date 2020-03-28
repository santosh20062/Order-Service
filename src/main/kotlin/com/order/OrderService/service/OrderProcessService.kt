package com.order.OrderService.service

import com.order.OrderService.dao.OrderRepository
import com.order.OrderService.model.Order
import com.order.OrderService.model.OrderAudit
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Service("orderProcessService")
class OrderProcessService(@Autowired val orderRepository: OrderRepository,
                          @Autowired val orderProcessAuditService: OrderProcessAuditService,
                          @Autowired val producer: Producer
) {

    fun findAll(): Flux<Order> {
        return orderRepository.findAll()
    }

    fun createOrder(order: Order): Mono<Order> {


        var orderAudit: OrderAudit = OrderAudit(null, null, order.orderName, order.orderType, order.orderStatus, order.description, order.price, order.quantity)
        orderAudit.priceNew = order.price;
        orderAudit.quantityNew = order.quantity
        orderAudit.createdBy = "Admin"
        orderAudit.status = "Created"
        orderAudit.createdDate = LocalDateTime.now()
        orderAudit.updatedBy = "Admin"

        println("order :"+order)

        println("orderAudit :"+orderAudit)

        producer.sendMessage(orderAudit)

        return orderProcessAuditService.createOrderAudit(orderAudit)
                .flatMap { orderRepository.save(order) }
       // return  orderRepository.save(order)

    }


    fun getById(id: String): Mono<ResponseEntity<Order>> {
        return orderRepository.findById(id)
                .map({ request -> ResponseEntity.ok<Order>(request) })
                .defaultIfEmpty(ResponseEntity.notFound().build<Order>())
    }
}