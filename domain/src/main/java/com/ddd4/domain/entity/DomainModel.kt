package com.ddd4.domain.entity

data class DomainModel(
    val id: Int,
    val product: String,
    val expirationDate: String,
    val dday: Long
)