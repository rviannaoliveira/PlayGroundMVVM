android {
    dataBinding {
        isEnabled = true
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(project(Depends.Module.di))
    implementation(project(Depends.Module.base))
    implementation(project(Depends.Module.shared))
    implementation(project(Depends.Module.cache))
    implementation(project(Depends.Module.networking))

    Depends.daggerArray.forEach { implementation(it) }
    Depends.processorDaggerArray.forEach { kapt(it) }
    Depends.rxArray.forEach { implementation(it) }
    implementation(Depends.Picasso.picasso)

    Depends.kotlinArray.forEach { implementation(it) }
    Depends.supportArray.forEach { implementation(it) }
    Depends.unitTestArray.forEach { testImplementation(it) }
    Depends.androidTestArray.forEach { androidTestImplementation(it) }
}
