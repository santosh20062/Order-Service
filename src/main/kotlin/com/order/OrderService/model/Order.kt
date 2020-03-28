package com.order.OrderService.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Order(@Id val id : String ?= null,

                 var orderName: String?,

                 var orderType: String?,

                 var orderStatus : String,

                 var description: String?,


                 var price:  Double?,

                 var quantity: Long?) {

}