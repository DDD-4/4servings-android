package com.ddd4.domain.entity

data class DropitDomainModel(
    val id: Int,
    val product: String,
    val expirationDate: String,
    val dday: Long
)