package com.ngr.appnews.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ngr.security.biometrics.BiometricAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

data class MainState(
    val requireBiometric: Boolean? = null,
)

sealed class MainEvent(){
    class Auth(val fragmentActivity: FragmentActivity) : MainEvent()
}

class MainActivityViewModel(
    private val biometricAuth: BiometricAuth
) : ViewModel() {

    private val mutableViewState: MutableStateFlow<MainState> = MutableStateFlow(MainState(null))

    val viewState: MutableStateFlow<MainState>
        get() = mutableViewState

    fun dispatch(event: MainEvent){
        when(event){
            is MainEvent.Auth -> callBiometrics(event.fragmentActivity)
        }
    }

    private fun callBiometrics(fragmentActivity: FragmentActivity) {
        viewModelScope.launch {
            mutableViewState.value = MainState(biometricAuth.canAccess(fragmentActivity))
        }
    }

}