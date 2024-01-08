package com.ngr.network.dto

import com.ngr.data.model.Source
import com.ngr.network.config.Mapper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceDTO(
    @SerialName("id") var id: String? = "",
    @SerialName("name") var name: String? = "",
)

class SourceMapper() : Mapper<Source, SourceDTO> {
    override fun mapToDomainModel(dto: SourceDTO) = Source(
        id = dto.id ?: "",
        name = dto.name ?: ""
    )

}
