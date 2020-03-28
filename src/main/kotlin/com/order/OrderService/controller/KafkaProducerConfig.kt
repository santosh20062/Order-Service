package com.order.OrderService.controller


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import kotlin.collections.HashMap
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer

import org.springframework.kafka.core.ProducerFactory



@Configuration
class KafkaProducerConfig {


    @Bean
    fun producerFactory(): ProducerFactory<String, String> {
        val configProps = HashMap<String,Any>()
        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9096"
        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        return DefaultKafkaProducerFactory<String,String>(configProps)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, String> {
        return KafkaTemplate(producerFactory())
    }
}