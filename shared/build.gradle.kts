android {
    dataBinding {
        isEnabled = true
    }
}

dependencies {
    Depends.kotlinArray.forEach { implementation(it) }
    Depends.supportArray.forEach { implementation(it) }
    Depends.unitTestArray.forEach { testImplementation(it) }

}
