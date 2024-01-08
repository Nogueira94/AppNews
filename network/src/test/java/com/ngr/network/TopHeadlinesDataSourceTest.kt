package com.ngr.network

import com.ngr.data.model.TopHeadlines
import com.ngr.network.config.DataResponse
import com.ngr.network.datasource.TopHeadlinesDataSource
import com.ngr.network.dto.TopHeadlinesDTO
import com.ngr.network.dto.TopHeadlinesMapper
import com.ngr.network.service.TopHeadlinesService
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import topHeadlines
import topHeadlinesDTO

class TopHeadlinesDataSourceTest {

    private lateinit var service: TopHeadlinesService
    private lateinit var dataSource: TopHeadlinesDataSource
    private lateinit var topHeadlinesMapper: TopHeadlinesMapper

    @Before
    fun setUp() {
        service = mockk<TopHeadlinesService>()
        topHeadlinesMapper = mockk<TopHeadlinesMapper>(relaxed = true)
        dataSource = TopHeadlinesDataSource(service, topHeadlinesMapper, "")
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `data source should return topHeadlines when service is success`() = runTest {
        val mockedDto = topHeadlinesDTO
        val mockedDomain = topHeadlines

        coEvery { service.getTopHeadlines("") } returns mockedDto
        coEvery { topHeadlinesMapper.mapToDomainModel(mockedDto) } returns mockedDomain

        val result = dataSource.getTopHeadlines()

        assertTrue(result is DataResponse.Success)
        assertEquals(mockedDomain, (result as DataResponse.Success).data)

    }

    @Test
    fun `should return failure response when service call throws an exception`() = runTest {
        val mockException = Throwable("Mock error")
        coEvery { service.getTopHeadlines("") } throws mockException

        val result = dataSource.getTopHeadlines()

        assertTrue(result is DataResponse.Failure)
        assertEquals(mockException, (result as DataResponse.Failure).error)
    }

}