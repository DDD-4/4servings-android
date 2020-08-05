package com.ddd4.dropit.mapper

interface PresenterMapper<V, D> {
    fun toPresenterEntity(type: D): V
    fun toDomainEntity(type: V): D
}