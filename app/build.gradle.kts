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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {


    // Compose

    //Preferred compose bom
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.activity.compose)

   // implementation(libs.androidx.ui)

    implementation(libs.androidx.compose.runtime)

    implementation(libs.androidx.compose.ui)

    implementation(libs.androidx.compose.foundation)

    // Core

    implementation(libs.androidx.material3)

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

    // DataStore
    implementation(libs.datastore.preferences)

    // Tests
    testImplementation(libs.junit)

    androidTestImplementation(platform(libs.androidx.compose.bom))

    androidTestImplementation(libs.androidx.ui.test.junit4)

    androidTestImplementation(libs.androidx.junit)

    androidTestImplementation(libs.androidx.espresso.core)

    debugImplementation(libs.androidx.ui.test.manifest)





}

