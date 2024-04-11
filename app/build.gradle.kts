plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    alias(libs.plugins.kotlinParcelize)
    alias(libs.plugins.navigation.safeargs.kotlin)
    alias(libs.plugins.daggerHilt)
}

android {
    namespace = "com.example.countries_wlmt"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.countries_wlmt"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        signingConfig = signingConfigs.getByName("debug")
        buildConfigField(
            "String", "BASE_URL", "\"https://gist.githubusercontent.com/peymano-wmt/\""
        )
        buildConfigField(
            "int", "TIMEOUT_SECONDS", "10"
        )
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField(
                "String", "BASE_URL", "\"https://gist.githubusercontent.com/peymano-wmt/\""
            )
            buildConfigField(
                "int", "TIMEOUT_SECONDS", "10"
            )
        }
        release {
            buildConfigField(
                "String", "BASE_URL", "\"https://gist.githubusercontent.com/peymano-wmts/\""
            )
            buildConfigField(
                "int", "TIMEOUT_SECONDS", "10"
            )
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
        buildConfig = true
        viewBinding = true
    }

    dataBinding {
        addKtx = true
    }
}

dependencies {
    //UI
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.swiperefreshlayout)
    //Lifecycle
    implementation(libs.lifecycle)
    //Navigation
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.fragment)
    //DI
    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.fragment.testing)
    kapt(libs.dagger.hilt.android.compiler)
    //Network
    implementation(libs.adapter.rxjava)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)
    implementation(libs.converter.scalars)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.retrofit2.kotlin.coroutines.adapter)
    //Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation (libs.kotlinx.coroutines.test)
    testImplementation (libs.androidx.core.testing)
}