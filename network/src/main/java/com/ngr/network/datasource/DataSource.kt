package com.ngr.network.datasource

import com.ngr.network.config.DataResponse
import com.ngr.network.config.Mapper
import org.koin.core.component.KoinComponent

open class DataSource : KoinComponent {

    suspend fun <DTO, Domain> apiCall(
        endpoint: suspend () -> DTO,
        mapper: Mapper<Domain, DTO>
    ) : DataResponse<Domain> {
        return try {
            val dto = endpoint.invoke()
            val domain = mapper.mapToDomainModel(dto)

            DataResponse.Success(domain)
        } catch (e: Throwable){
            DataResponse.Failure(e)
        }
    }
}