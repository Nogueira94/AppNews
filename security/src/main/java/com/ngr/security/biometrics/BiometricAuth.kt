package com.ngr.security.biometrics

import android.content.Context
import android.util.MutableBoolean
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.util.concurrent.CompletableFuture
import kotlin.coroutines.resume

private const val NO_FINGERPRINT_ENROLLED = 11

class BiometricAuth(private val context: Context) {

    suspend fun canAccess(fragment: FragmentActivity) : Boolean {
            return when (BiometricManager.from(context).canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
                BiometricManager.BIOMETRIC_SUCCESS -> validBiometric(fragment)
                else ->  true
            }
    }

    private suspend fun validBiometric(fragment: FragmentActivity) : Boolean {
        return suspendCancellableCoroutine { continuation ->
            val biometricPrompt = BiometricPrompt(
                fragment,
                ContextCompat.getMainExecutor(context),
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                        if (errorCode != NO_FINGERPRINT_ENROLLED) {
                            continuation.resume(false)
                        } else {
                            continuation.resume(true)
                        }
                    }

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        continuation.resume(true)
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        continuation.resume(false)
                    }
                }
            )
            biometricPrompt.authenticate(
                BiometricPrompt.PromptInfo.Builder()
                    .setTitle("Put your fingerprint")
                    .setNegativeButtonText("Cancel")
                    .build()
            )
            continuation.invokeOnCancellation {
                continuation.resume(true)
            }
        }
    }
}