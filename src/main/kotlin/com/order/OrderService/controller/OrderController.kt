package com.order.OrderService.controller

import com.order.OrderService.model.Order
import com.order.OrderService.service.OrderProcessService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

//@CrossOrigin(origins = arrayOf("http://localhost:3000"), maxAge =  36000)
@RestController
@RequestMapping("/orders")
class OrderController(@Autowired val orderProcessService: OrderProcessService) {

    @GetMapping
    fun getAll() : Flux<Order> {
        return orderProcessService.findAll()
    }






    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    fun createBooks(@RequestBody order: Order): Mono<Order> {

        return orderProcessService.createOrder(order)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable(value = "id") id: String): Mono<ResponseEntity<Order>> {

        return orderProcessService.getById(id)
    }

}