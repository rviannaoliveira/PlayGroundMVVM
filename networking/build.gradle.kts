android {
    buildTypes {
        getByName("release") {
            buildConfigField("String", "BASE_URL", "\"http://grupozap-code-challenge.s3-website-us-east-1.amazonaws.com/sources/\"")
        }
        getByName("debug") {
            buildConfigField("String", "BASE_URL", "\"http://grupozap-code-challenge.s3-website-us-east-1.amazonaws.com/sources/\"")
        }
    }
}

dependencies {
    implementation(project(Depends.Module.di))

    Depends.daggerArray.forEach { implementation(it) }
    Depends.processorDaggerArray.forEach { kapt(it) }
    Depends.okHttpArray.forEach { implementation(it) }
    Depends.retrofitArray.forEach { implementation(it) }
    Depends.rxArray.forEach { implementation(it) }

    Depends.kotlinArray.forEach { implementation(it) }
    Depends.supportArray.forEach { implementation(it) }
    Depends.unitTestArray.forEach { testImplementation(it) }
    Depends.androidTestArray.forEach { androidTestImplementation(it) }
}
