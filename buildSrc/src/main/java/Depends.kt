import kotlin.reflect.full.memberProperties

object Depends {
    const val runnerPackage = "androidx.test.runner.AndroidJUnitRunner"

    object Module {
        const val di = ":di"
        const val base = ":base"
        const val main = ":main"
        const val networking = ":networking"
        const val shared = ":shared"
        const val components = ":components"
        const val cache = ":cache"
        const val detail = ":detail"
    }

    val modules: List<String> by lazy {
        Module::class.memberProperties.map {
            it.name
        }
    }

    object Android {
        const val gradle_plugin = "com.android.tools.build:gradle:${Versions.gradle_plugin_version}"
    }

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_version}"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin_version}"
        const val gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.androidx_core_ktx}"
    }

    object Test {
        const val core = "androidx.test:core:${Versions.test_runner_version}"
        const val junit = "junit:junit:${Versions.test_junit_version}"
        const val runner = "androidx.test:runner:${Versions.test_runner_version}"
        const val viewModelTest = "androidx.arch.core:core-testing:${Versions.viewmodel_version}"
        const val mockk = "io.mockk:mockk:${Versions.mockk}"
    }

    object AndroidTest {
        const val runner = "androidx.test:runner:${Versions.test_runner_version}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.test_espresso_version}"
        const val rules = "androidx.test:rules:${Versions.test_rules_version}"
        const val hamcrest = "org.hamcrest:hamcrest-library:${Versions.test_hamcrest}"
        const val uiautomator = "androidx.test.uiautomator:uiautomator:${Versions.test_uiautomator}"
        const val ext = "androidx.test.ext:junit:${Versions.test_ext}"
    }

    object Support {
        const val v7 = "androidx.appcompat:appcompat:${Versions.support_version}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_motion_version}"
        const val cardView = "androidx.cardview:cardview:${Versions.cardview_version}"
        const val design = "com.google.android.material:material:${Versions.material_version}"
        const val animation = "androidx.dynamicanimation:dynamicanimation:1.0.0"
    }

    object KtxExtentions {
        const val activity = "androidx.activity:activity-ktx:${Versions.activity_ktx_version}"
        const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment_ktx_version}"
    }

    object Jacoco {
        const val jacoco = "org.jacoco:org.jacoco.core:${Versions.jacoco_version}"
    }

    object Fabric {
        const val fabric = "io.fabric.tools:gradle:${Versions.fabric_version}"
    }

    object MultiDex {
        const val multidex = "androidx.multidex:multidex:${Versions.multidex_version}"
    }

    object Dagger {
        const val core = "com.google.dagger:dagger:${Versions.dagger_version}"
        const val android = "com.google.dagger:dagger-android:${Versions.dagger_version}"
        const val support = "com.google.dagger:dagger-android-support:${Versions.dagger_version}"
        const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger_version}"
        const val processor = "com.google.dagger:dagger-android-processor:${Versions.dagger_version}"
    }

    object RxJava {
        const val android = "io.reactivex.rxjava2:rxandroid:${Versions.rxandroid_version}"
        const val kotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlin_version}"
        const val core = "io.reactivex.rxjava2:rxjava:${Versions.rxjava_version}"
    }

    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
        const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi_version}"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit_version}"
        const val rxAdapter = "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Versions.rxjava_adapter}"
    }

    object OkHttp3 {
        const val core = "com.squareup.okhttp3:okhttp:${Versions.okhttp_version}"
        const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_version}"
    }

    object ViewModel {
        const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.viewmodel_version}"
        const val lifecycleConvertRxToLivedata = "androidx.lifecycle:lifecycle-reactivestreams:${Versions.viewmodel_version}"
    }


    object Picasso {
        const val picasso = "com.squareup.picasso:picasso:${Versions.picasso_version}"
    }

    val kotlinArray = arrayOf(
            Kotlin.stdlib,
            Kotlin.reflect,
            Kotlin.coreKtx
    )

    val supportArray = arrayOf(
            Support.v7,
            Support.constraintLayout,
            Support.cardView,
            Support.design,
            Support.animation
    )

    val okHttpArray = arrayOf(
            OkHttp3.core,
            OkHttp3.interceptor
    )

    val retrofitArray = arrayOf(
            Retrofit.core,
            Retrofit.moshi,
            Retrofit.moshiConverter,
            Retrofit.rxAdapter
    )

    val rxArray = arrayOf(
            RxJava.android,
            RxJava.core
    )

    val processorDaggerArray = arrayOf(
            Dagger.compiler,
            Dagger.processor
    )


    val daggerArray = arrayOf(
            Dagger.core,
            Dagger.android,
            Dagger.support
    )

    val viewModelArray = arrayOf(
            ViewModel.lifecycleExtensions,
            ViewModel.lifecycleConvertRxToLivedata
    )

    val unitTestArray = arrayOf(
            Test.core,
            Test.junit,
            Test.viewModelTest,
            Test.mockk
    )

    val androidTestArray = arrayOf(
            AndroidTest.espresso,
            AndroidTest.hamcrest,
            AndroidTest.rules,
            AndroidTest.uiautomator,
            AndroidTest.runner
    )
}