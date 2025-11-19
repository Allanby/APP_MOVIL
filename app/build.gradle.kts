import com.android.build.api.dsl.Packaging

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.21"
}

android {
    namespace = "com.example.adminrh"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.adminrh"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packaging {
        resources {
            excludes.add("META-INF/INDEX.LIST")
            excludes.add("META-INF/DEPENDENCIES")
            excludes.add("META-INF/LICENSE")
            excludes.add("META-INF/LICENSE.txt")
            excludes.add("META-INF/license.txt")
            excludes.add("META-INF/NOTICE")
            excludes.add("META-INF/NOTICE.txt")
            excludes.add("META-INF/notice.txt")
            excludes.add("META-INF/ASL2.0")
            excludes.add("META-INF/*.kotlin_module")
            excludes.add("META-INF/io.netty.versions.properties")
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
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "2.2.21"
    }
}


dependencies {
    implementation(libs.chart)    // ===================================
    // LIBRERÍAS DE GRÁFICOS (CORREGIDO)
    // ===================================
    // VICO (para Jetpack Compose)
    implementation(libs.vico.compose)
    implementation(libs.vico.compose.m3) // ¡LA CORRECTA PARA MATERIAL 3!
    // implementation(libs.vico.compose.m2) // ¡LA LÍNEA PROBLEMÁTICA HA SIDO ELIMINADA!
    implementation("com.patrykandpatrick.vico:compose:2.3.4") // O la versión más reciente
    implementation("com.patrykandpatrick.vico:core:2.3.4")

    // OTRAS LIBRERÍAS DE GRÁFICOS (si las sigues usando)
    implementation("com.github.AnyChart:AnyChart-Android:1.1.5")
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    // ==============================
    // 🧩 Jetpack Compose
    // ==============================
    implementation(platform("androidx.compose:compose-bom:2025.01.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.5")
    implementation("androidx.navigation:navigation-compose:2.8.2")
    implementation("androidx.compose.animation:animation")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material:material-icons-extended:1.5.0")
    implementation(libs.androidx.runtime.livedata)

    // ==============================
    // 🤖 Core Android & Utils
    // ==============================
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation("androidx.activity:activity-ktx:1.9.3")
    implementation(libs.androidx.constraintlayout)

    // Layout y navegación (para XML)
    implementation("androidx.drawerlayout:drawerlayout:1.2.0")
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.2.0")
    implementation("androidx.fragment:fragment-ktx:1.8.4")

    // Lifecycle + Coroutines
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    // Networking (Retrofit + OkHttp)
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    debugImplementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Imágenes
    implementation("io.coil-kt:coil:2.6.0")

    // Firebase (si lo usas)
    // implementation(libs.firebase.appdistribution.gradle) // Descomentar si realmente lo usas

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

