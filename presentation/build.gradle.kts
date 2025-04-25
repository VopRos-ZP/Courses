plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "ru.vopros.courses.presentation"
    compileSdk = 35

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        jvmTarget = "${JavaVersion.VERSION_11}"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    api(project(":domain"))
    api(project(":data"))
    /** Core **/
    api(libs.androidx.core.ktx)
    api(libs.androidx.appcompat)
    api(libs.androidx.lifecycle.runtime.ktx)
    /** Compose **/
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.ui)
    api(libs.androidx.ui.graphics)
    debugImplementation(libs.androidx.ui.tooling)
    api(libs.androidx.ui.tooling.preview)
    api(libs.androidx.material3)
    /** Coroutines **/
    api(libs.kotlinx.coroutines.android)
    /** Koin **/
    api(libs.koin.android)
    api(libs.koin.compose)
    /** Test **/
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}