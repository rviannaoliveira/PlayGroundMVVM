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
}

dependencies {
    implementation(project(Depends.Module.di))
    implementation(project(Depends.Module.base))
    implementation(project(Depends.Module.networking))
    implementation(project(Depends.Module.shared))
    implementation(project(Depends.Module.components))
    implementation(project(Depends.Module.cache))

    Depends.daggerArray.forEach { implementation(it) }
    Depends.processorDaggerArray.forEach { kapt(it) }
    Depends.retrofitArray.forEach { implementation(it) }
    Depends.rxArray.forEach { implementation(it) }
    implementation(Depends.Picasso.picasso)

    Depends.kotlinArray.forEach { implementation(it) }
    Depends.supportArray.forEach { implementation(it) }
    Depends.unitTestArray.forEach { testImplementation(it) }
    Depends.androidTestArray.forEach { androidTestImplementation(it) }
}
