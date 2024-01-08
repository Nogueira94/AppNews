import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "com.ngr.appnews"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.ngr.appnews"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get().toString()

        val properties = Properties()
        properties.load(FileInputStream(file("../local.properties")))
        buildConfigField("String","API_KEY",properties.getProperty("API_KEY"))

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    flavorDimensions.add("source")
    productFlavors{
        create("bbc"){
            dimension = "source"
            applicationIdSuffix = ".bbc"
            versionNameSuffix = "-bbc"
            buildConfigField("String","SOURCE_PARAM","\"bbc-news\"")
        }
        create("abc"){
            dimension = "source"
            applicationIdSuffix = ".abc"
            versionNameSuffix = "-abc"
            buildConfigField("String","SOURCE_PARAM","\"abc-news\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(projects.network)
    implementation(projects.data)
    implementation(projects.designsystem)
    implementation(projects.domain)

    implementation(libs.koinCompose)

    implementation(libs.coilCompose)

    implementation(libs.androidxCoreKtx)
    implementation(libs.androidxLifecycleRuntimeKtx)
    implementation(libs.androidxActivityCompose)
    implementation(libs.androidxLifecycleViewModelCompose)
    implementation(libs.androidxComposeMaterialIconsExtended)
    implementation(libs.androidxActivityCompose)
    implementation(libs.androidxAccompanistSwiperefresh)
    implementation(platform(libs.androidxComposeBom))
    implementation(libs.androidxComposeUi)
    implementation(libs.androidxComposeUiGraphics)
    implementation(libs.androidxComposeUiToolingPreview)
    implementation(libs.androidxComposeMaterial3)

    implementation(libs.kotlinxSerializationConverter)
    implementation(libs.kotlinxSerializationJson)
    implementation(libs.androidxNavigationCompose)

    implementation(libs.retrofit)
    testImplementation(libs.mockk)
    testImplementation(libs.junit)
    testImplementation(libs.coroutinesTest)
}