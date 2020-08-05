package com.ddd4.data.mapper

interface DataMapper<E, D> {
    fun toDomainEntity(type: E): D
    fun toDataEntity(type: D): E
}
