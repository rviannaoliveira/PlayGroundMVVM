dependencies {
    implementation(Depends.Picasso.picasso)
    implementation(project(Depends.Module.shared))

    Depends.daggerArray.forEach { implementation(it) }
    Depends.processorDaggerArray.forEach { kapt(it) }

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
