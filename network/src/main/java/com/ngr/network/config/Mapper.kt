package com.ngr.network.config

interface Mapper<DomainModel,DTO> {
    fun mapToDomainModel(dto: DTO) : DomainModel
}