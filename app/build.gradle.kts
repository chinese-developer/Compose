plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
//    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "com.android.sdk"
        minSdk = 21
        targetSdk = 30
        versionCode  = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
    kapt {
        correctErrorTypes = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.compose
    }
    packagingOptions {
        resources.excludes.apply {
            add("META-INF/AL2.0")
            add("META-INF/LGPL2.1")
        }
    }
    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
}

dependencies {

    // Keep updating
    implementation("com.github.Gurupreet:FontAwesomeCompose:${Dependencies.fontAwesomeCompose}")
    implementation("androidx.core:core-ktx:${Dependencies.coreKtx}")
    implementation("androidx.appcompat:appcompat:${Dependencies.appcompat}")
    implementation("com.google.android.material:material:${Dependencies.material}")

    // Kotlin
    debugImplementation("org.jetbrains.kotlin:kotlin-reflect:${Dependencies.Kotlin.version}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Dependencies.Kotlin.version}")

    // Compose
    implementation("androidx.compose.ui:ui:${Dependencies.compose}")
    implementation("androidx.compose.material:material:${Dependencies.compose}")
    implementation("androidx.compose.material:material-icons-extended:${Dependencies.compose}")
    implementation("androidx.compose.runtime:runtime-livedata:${Dependencies.compose}")
    implementation("androidx.compose.ui:ui-tooling:${Dependencies.compose}")
    debugImplementation("androidx.compose.ui:ui-tooling:${Dependencies.compose}")

    // Added starting from compose alpha-12
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Dependencies.lifecycleViewModelCompose}")
    implementation("androidx.activity:activity-compose:${Dependencies.activityCompose}")
    implementation("androidx.constraintlayout:constraintlayout-compose:${Dependencies.constraintLayoutCompose}")
    implementation("androidx.navigation:navigation-compose:${Dependencies.navCompose}")

    // Paging
    implementation("androidx.paging:paging-runtime:${Dependencies.paging}")
    // Paging Jetpack Compose Integration
    implementation("androidx.paging:paging-compose:${Dependencies.pagingCompose}")

    // Lottie
    implementation("com.airbnb.android:lottie:${Dependencies.lottie}")
    // Lottie Jetpack Compose Integration
    implementation("com.airbnb.android:lottie-compose:${Dependencies.lottieCompose}")

    // Stable
    implementation("androidx.palette:palette-ktx:${Dependencies.paletteKtx}")
    implementation("androidx.multidex:multidex:${Dependencies.multidex}")

    
    //Network libs
    // Room
    implementation("androidx.room:room-runtime:${Dependencies.room}")
    implementation("androidx.room:room-ktx:${Dependencies.room}")
    kapt("androidx.room:room-compiler:${Dependencies.room}")
    // annotationProcessor "android.arch.persistence.room:compiler:$room_version"

    // Hilt
//    implementation("com.google.dagger:hilt-android:${Dependencies.hilt}")
//    kapt("com.google.dagger:hilt-android-compiler:${Dependencies.hilt}")
//    implementation("androidx.hilt:hilt-lifecycle-viewmodel:${Dependencies.lifecycleViewModelHilt}")
//    kapt("androidx.hilt:hilt-compiler:${Dependencies.lifecycleViewModelHilt}")

    implementation("com.google.accompanist:accompanist-coil:${Dependencies.accompanistCoil}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Dependencies.lifecycleRuntimeKtx}")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:${Dependencies.retrofit}")
    implementation("com.squareup.retrofit2:converter-gson:${Dependencies.retrofit}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Dependencies.loggingInterceptor}")
    implementation("com.google.code.gson:gson:${Dependencies.gson}")
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Dependencies.coroutine}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Dependencies.coroutine}")
    // Lifecycle + Viewmodel
    implementation("androidx.lifecycle:lifecycle-extensions:${Dependencies.androidLifecycleGrouped}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Dependencies.androidLifecycleGrouped}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Dependencies.androidLifecycleGrouped}")


    //Test libs
    testImplementation("org.junit.jupiter:junit-jupiter-api:${Dependencies.junitJupiterApi}")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:${Dependencies.junitJupiterEngine}")
    testImplementation("com.google.truth:truth:${Dependencies.truth}")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:${Dependencies.Kotlin.version}")
    testImplementation("org.jetbrains.kotlin:kotlin-test:${Dependencies.Kotlin.version}")

    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Dependencies.compose}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${Dependencies.compose}")
    androidTestImplementation("androidx.activity:activity-compose:${Dependencies.activityCompose}")
    androidTestImplementation("androidx.test.ext:junit:${Dependencies.androidXJunit}")

}