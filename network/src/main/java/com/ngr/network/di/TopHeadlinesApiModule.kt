package com.ngr.network.di

import com.ngr.network.config.NetworkClient
import com.ngr.network.datasource.TopHeadlinesDataSource
import com.ngr.network.dto.ArticleMapper
import com.ngr.network.dto.SourceMapper
import com.ngr.network.dto.TopHeadlinesMapper
import com.ngr.network.service.TopHeadlinesService
import org.koin.dsl.module
import retrofit2.Retrofit

val topHeadlinesApiModule = module {


    single { SourceMapper() }
    single { ArticleMapper(get()) }
    single { TopHeadlinesMapper(get()) }

    single<TopHeadlinesService>{
        val client: NetworkClient<Retrofit> = get()
        client.client().create(TopHeadlinesService::class.java)
    }
    single<TopHeadlinesDataSource>{
        val client: NetworkClient<Retrofit> = get()
        TopHeadlinesDataSource(
            service = get(),
            topHeadlinesMapper = get(),
            sourceParam = client.sourceParam()
        )
    }
}