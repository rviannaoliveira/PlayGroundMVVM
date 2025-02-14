android {
    dataBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(project(Depends.Module.di))
    implementation(project(Depends.Module.main))
    implementation(project(Depends.Module.base))
    implementation(project(Depends.Module.networking))
    implementation(project(Depends.Module.shared))
    implementation(project(Depends.Module.components))
    implementation(project(Depends.Module.cache))
    implementation(project(Depends.Module.detail))

    Depends.daggerArray.forEach { implementation(it) }
    Depends.processorDaggerArray.forEach { kapt(it) }
    Depends.okHttpArray.forEach { implementation(it) }
    Depends.retrofitArray.forEach { implementation(it) }
    Depends.rxArray.forEach { implementation(it) }

    Depends.kotlinArray.forEach { implementation(it) }
    Depends.supportArray.forEach { implementation(it) }
    Depends.unitTestArray.forEach { testImplementation(it) }
    Depends.androidTestArray.forEach { androidTestImplementation(it) }
    Depends.processorDaggerArray.forEach { kaptAndroidTest(it) }
}
