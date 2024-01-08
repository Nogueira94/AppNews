package com.ngr.domain.di

import com.ngr.domain.repository.TopHeadlinesRepository
import com.ngr.domain.repository.TopHeadlinesRepositoryImpl
import com.ngr.domain.usecase.ArticlesListUseCase
import org.koin.dsl.module

val domainModule = module{
    single<TopHeadlinesRepository> {
        TopHeadlinesRepositoryImpl(
            remoteDataSource = get()
        )
    }

    single<ArticlesListUseCase>{
        ArticlesListUseCase(repository = get())
    }
}