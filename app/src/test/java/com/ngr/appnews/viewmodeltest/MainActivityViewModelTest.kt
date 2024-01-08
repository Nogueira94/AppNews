package com.ngr.appnews.viewmodeltest

import com.ngr.appnews.utils.MainDispatcherRule
import com.ngr.appnews.viewmodel.MainActivityViewModel
import com.ngr.appnews.viewmodel.MainEvent
import com.ngr.appnews.viewmodel.MainState
import com.ngr.security.biometrics.BiometricAuth
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainActivityViewModelTest {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var biometricAuth: BiometricAuth

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        biometricAuth = mockk<BiometricAuth>(relaxed = true)
        viewModel = MainActivityViewModel(biometricAuth)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `callBiometrics success`() = runTest {
        coEvery { biometricAuth.canAccess(any()) } returns true

        val viewModel = MainActivityViewModel(biometricAuth)
        val expected = MainState(true)

        viewModel.dispatch(MainEvent.Auth(mockk(relaxed = true)))

        advanceUntilIdle()

        Assert.assertEquals(expected, viewModel.viewState.value)

    }

    @Test
    fun `callBiometrics failure`() = runTest {
        coEvery { biometricAuth.canAccess(any()) } returns false

        val viewModel = MainActivityViewModel(biometricAuth)
        val expected = MainState(false)

        viewModel.dispatch(MainEvent.Auth(mockk(relaxed = true)))

        advanceUntilIdle()

        Assert.assertEquals(expected, viewModel.viewState.value)
    }

//    @Test
//    fun `viewstate test`() = runTest {
//        // Arrange
//        val biometricAuth = mockk<BiometricAuth>()
//        coEvery { biometricAuth.canAccess(any()) } returns true
//
//        val viewModel = MainActivityViewModel(biometricAuth)
//        val flow = flow { emit(MainState(true)) }.flowOn(this.coroutineContext)
//
//        // Act
//        viewModel.viewState.collect(flow)
//        viewModel.dispatch(MainEvent.Auth(mockk(relaxed = true)))
//
//        // Assert
//        coVerify { biometricAuth.canAccess(any()) }
//        verify { observer.onChanged(MainState(true)) }
//    }
}