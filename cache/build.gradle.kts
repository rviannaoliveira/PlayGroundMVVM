dependencies {
    implementation(project(Depends.Module.di))

    Depends.daggerArray.forEach { implementation(it) }
    Depends.processorDaggerArray.forEach { kapt(it) }
    Depends.rxArray.forEach { implementation(it) }

    Depends.kotlinArray.forEach { implementation(it) }
    Depends.supportArray.forEach { implementation(it) }
    Depends.unitTestArray.forEach { testImplementation(it) }
    Depends.androidTestArray.forEach { androidTestImplementation(it) }
}
android {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
