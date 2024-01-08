package com.ngr.security.biometrics

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

private const val NO_FINGERPRINT_ENROLLED = 11

class BiometricAuth(private val context: Context){

    fun canAccess(fragment: FragmentActivity, callback: (Boolean) -> Unit) {
        when (BiometricManager.from(context).canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS -> validBiometric(fragment,callback)
            else ->  callback.invoke(true)
        }
    }

    private fun validBiometric(fragment: FragmentActivity, callback: (Boolean) -> Unit) {
        val biometricPrompt = BiometricPrompt(
            fragment,
            ContextCompat.getMainExecutor(context),
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    if(errorCode != NO_FINGERPRINT_ENROLLED){
                        callback.invoke(false)
                    } else {
                        callback.invoke(true)
                    }
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    callback.invoke(true)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    callback.invoke(false)
                }
            }
        )
        biometricPrompt.authenticate(
            BiometricPrompt.PromptInfo.Builder()
                .setTitle("Put your fingerprint")
                .setNegativeButtonText("Cancel")
                .build()
        )
    }

}