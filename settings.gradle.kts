pluginManagement {
    repositories {
        if (providers.gradleProperty("composeNativeLocal").orNull == "true") {
            mavenLocal()
        }
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        if (providers.gradleProperty("composeNativeLocal").orNull == "true") {
            mavenLocal()
        }
        google()
        mavenCentral()
    }
}

plugins {
    id("com.gradle.develocity") version "4.5.1"
    id("org.gradle.toolchains.foojay-resolver-convention").version("1.0.0")
}

develocity {
    buildScan {
        termsOfUseUrl.set("https://gradle.com/help/legal-terms-of-use")
        termsOfUseAgree.set("yes")

        publishing.onlyIf { context ->
            context.buildResult.failures.isNotEmpty() && !System.getenv("CI").isNullOrEmpty()
        }
    }
}

rootProject.name = "MaterialKolor"

include(
    ":material-kolor",
    ":material-color-utilities",
    ":mcu-upstream",
    ":builder:shared",
    ":builder:android",
)
