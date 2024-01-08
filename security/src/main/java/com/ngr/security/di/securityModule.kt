package com.ngr.security.di

import com.ngr.security.biometrics.BiometricAuth
import org.koin.dsl.module

val securityModule = module {
    single<BiometricAuth> { BiometricAuth(get()) }
}