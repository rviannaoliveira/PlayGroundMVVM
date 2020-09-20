import Depends.modules

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

        lintOptions {
            isCheckReleaseBuilds = false
            isCheckDependencies = true
            isCheckAllWarnings = true
            isWarningsAsErrors = true
            isIgnoreWarnings = true
            isAbortOnError = false
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

    configure<com.android.build.gradle.BaseExtension> {
        buildTypes {
            getByName("release") {
                buildConfigField("String", "BASE_URL", "\"http://grupozap-code-challenge.s3-website-us-east-1.amazonaws.com/sources/\"")
            }

            getByName("debug") {
                buildConfigField("String", "BASE_URL", "\"http://grupozap-code-challenge.s3-website-us-east-1.amazonaws.com/sources/\"")
            }
        }
    }

}




tasks.register("clean").configure {
    delete("build")
}
