plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    dataBinding {
        isEnabled = true
    }

    compileSdkVersion(Versions.compileSdkVersion)
    buildToolsVersion(Versions.buildToolsVersion)

    defaultConfig {
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = Depends.runnerPackage

        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    Depends.kotlinArray.forEach { implementation(it) }
    Depends.supportArray.forEach { implementation(it) }
    Depends.unitTestArray.forEach { testImplementation(it) }
    Depends.androidTestArray.forEach { androidTestImplementation(it) }
}
