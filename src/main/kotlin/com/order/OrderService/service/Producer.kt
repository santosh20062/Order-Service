package com.order.OrderService.service



import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.util.StdDateFormat
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.order.OrderService.model.OrderAudit
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate


import org.springframework.stereotype.Service



@Service
class Producer(@Autowired val kafkaTemplate : KafkaTemplate<String,String>? = null) {





    val jsonMapper = ObjectMapper().apply {
        registerKotlinModule()
        disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        setDateFormat(StdDateFormat())
    }


    fun sendMessage(orderAudit: OrderAudit) : String {

        val orderAuditString = jsonMapper.writeValueAsString(orderAudit)


        this.kafkaTemplate!!.send( "order-topic",orderAuditString)

        return "Message Sent Successfully";
    }

}