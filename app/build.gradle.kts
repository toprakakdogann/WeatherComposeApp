plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

}

android {
    namespace = "com.example.weathercomposeapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.weathercomposeapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // Compose BOM (versiyonları tek yerden yönetir)
    implementation(platform("androidx.compose:compose-bom:2024.04.01"))
    implementation ("androidx.compose.ui:ui")
    implementation ("androidx.compose.ui:ui-tooling-preview")
    implementation ("androidx.compose.material3:material3")
    implementation ("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.activity:activity-compose:1.9.0")
// Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

// Coroutine destekli API çağrısı
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation ("androidx.compose.material:material-icons-extended:1.5.0") // veya Compose sürümüne uygun

    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    // Compose temel kütüphaneler
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // Debug için
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
