pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AppNews"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":designsystem")
include(":network")
include(":data")
include(":domain")
include(":common")
include(":security")
