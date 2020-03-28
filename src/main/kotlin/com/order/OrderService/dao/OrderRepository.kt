package com.order.OrderService.dao


import com.order.OrderService.model.Order
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository("orderRepository")
interface OrderRepository : ReactiveCrudRepository<Order,String> {


}