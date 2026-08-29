plugins {


    alias(libs.plugins.android.application)

    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.kotlin.serialization)

    alias(libs.plugins.hilt)

    alias(libs.plugins.ksp)


}

android {
    namespace = "com.policyboss.customer"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.policyboss.customer"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_11
//        targetCompatibility = JavaVersion.VERSION_11
//    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {


    // Compose
  // ⭐ Use enforcedPlatform from sneaking in
    implementation(enforcedPlatform(libs.androidx.compose.bom))
    //Preferred compose bom
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material3)

    // implementation(libs.androidx.ui)

    implementation(libs.androidx.compose.runtime)

    implementation(libs.androidx.compose.ui)

    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui.graphics)

    implementation(libs.androidx.material.icons.extended)

    // Core

    implementation(libs.androidx.material3)
    implementation(libs.androidx.runtime)

    implementation(libs.androidx.ui.graphics)

    implementation(libs.androidx.ui.tooling.preview)

    debugImplementation(libs.androidx.ui.tooling)


    implementation(libs.androidx.core.ktx)

    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.androidx.lifecycle.runtime.compose)

//   implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.androidx.appcompat)


    //implementation(libs.foundation)

    // Splash
    implementation(libs.androidx.core.splashscreen)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Hilt
    implementation(libs.hilt.android)

    ksp(libs.hilt.compiler)

    implementation(libs.androidx.hilt.navigation.compose)

    // ConstraintLayout
    implementation(libs.androidx.constraintlayout.compose)

    implementation(libs.androidx.constraintlayout)

    // Coil
    implementation(libs.coil.compose)

    // Retrofit
    implementation(libs.retrofit)

    implementation(libs.okhttp)

    implementation(libs.logging.interceptor)

    implementation(libs.kotlinx.serialization)

    implementation(libs.retrofit.serialization)


    //video Player
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)

    // DataStore
    implementation(libs.datastore.preferences)

  //Facebook-style shimmering
//    implementation(platform("androidx.compose:compose-bom:2026.02.00"))
//    implementation("androidx.compose.material3:material3")
//    implementation("androidx.compose.foundation:foundation")

   // implementation(platform(libs.androidx.compose.bom))

//    implementation(libs.androidx.compose.material3)
//    implementation(libs.androidx.compose.foundation)

    // Tests
    testImplementation(libs.junit)

    androidTestImplementation(platform(libs.androidx.compose.bom))

    androidTestImplementation(libs.androidx.ui.test.junit4)

    androidTestImplementation(libs.androidx.junit)

    androidTestImplementation(libs.androidx.espresso.core)

    debugImplementation(libs.androidx.ui.test.manifest)





}

