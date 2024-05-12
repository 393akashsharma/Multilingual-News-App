plugins {
    // Android application plugin
    id("com.android.application")
    // Kotlin Android plugin
    id("org.jetbrains.kotlin.android")
    // Kotlin Symbol Processing (KSP) plugin
    id("com.google.devtools.ksp")
    // Dagger Hilt Android plugin
    id("com.google.dagger.hilt.android")
    // Kotlin parcelize plugin for generating Parcelable implementations
    id("kotlin-parcelize")
    // Kotlin annotation processing plugin
    kotlin("kapt")
}

android {
    namespace = "com.astecnology.newspluse"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.astecnology.newspluse"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
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
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Core Kotlin extensions
    implementation("androidx.core:core-ktx:1.12.0")
    // Lifecycle runtime Kotlin extensions
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    // Activity Compose integration
    implementation("androidx.activity:activity-compose:1.8.2")

    // Jetpack Compose dependencies
    implementation(platform("androidx.compose:compose-bom:2024.04.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // Testing dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.04.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    testImplementation("org.mockito:mockito-core:5.1.1")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("androidx.test:core:1.5.0")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
    testImplementation("app.cash.turbine:turbine:0.5.2")

    // Splash API
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Navigation Compose integration
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Kotlin standard library
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.20")
    // Kotlin annotation processing plugin
    kapt("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")

    // Dagger Hilt dependencies
    implementation("com.google.dagger:hilt-android:2.49")
    kapt("com.google.dagger:hilt-compiler:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Retrofit for HTTP requests
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Coil for image loading
    implementation("io.coil-kt:coil-compose:2.4.0")

    // DataStore for data storage
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Compose Foundation
    implementation("androidx.compose.foundation:foundation:1.6.5")

    // Accompanist for additional Compose utilities
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.31.4-beta")

    // Paging 3 for pagination
    implementation("androidx.paging:paging-runtime-ktx:3.2.1")
    implementation("androidx.paging:paging-compose:3.2.1")

    // Room for local database
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    // Kotlin coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
}