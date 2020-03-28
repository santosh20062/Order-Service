package com.order.OrderService.service

import org.springframework.stereotype.Service
import java.io.IOException
import org.springframework.kafka.annotation.KafkaListener




@Service
class Consumer {

//    @KafkaListener(topics = ["order-topic"], groupId = "group_id")
//    @Throws(IOException::class)
//    fun consume(message: String) : String{
//        println(String.format("#### -> Consumed message -> %s", message))
//        return "Consumed Message"
//    }

}