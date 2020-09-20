import Depends.modules

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Depends.Kotlin.gradle)
        classpath(Depends.Android.gradle_plugin)
        classpath(kotlin("gradle-plugin", Versions.kotlin_version))
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        flatDir {
            dirs("libs")
        }
    }
    configureAndroid()
}


fun Project.configureAndroid() {
    val isAppModule = name == "app"

    when {
        isAppModule -> configureAppAndroid()
        modules.contains(name) -> configureAndroidLibrary()
        else -> return
    }

    apply(plugin = "kotlin-android")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "kotlin-android-extensions")

    configure<com.android.build.gradle.BaseExtension> {
        compileSdkVersion(Versions.compileSdkVersion)
        buildToolsVersion(Versions.buildToolsVersion)

        defaultConfig {
            minSdkVersion(Versions.minSdkVersion)
            targetSdkVersion(Versions.targetSdkVersion)
            versionCode = Versions.versionCode
            versionName = Versions.versionName
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables.useSupportLibrary = true
            multiDexEnabled = true
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }

    configure<org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension> {
        isExperimental = true
    }
}


fun Project.configureAppAndroid() {
    apply(plugin = "com.android.application")

    configure<com.android.build.gradle.BaseExtension> {
        defaultConfig {
            applicationId = Config.applicationId
        }

        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            }
        }
    }
}

fun Project.configureAndroidLibrary() {
    apply(plugin = "com.android.library")
}

tasks.register("clean").configure {
    delete("build")
}
