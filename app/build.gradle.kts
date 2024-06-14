plugins {
    id("com.android.application")
    id("com.google.android.gms.oss-licenses-plugin")
    id("com.google.dagger.hilt.android")
    id("kotlin-android")
    id("kotlin-kapt")
    alias(libs.plugins.ktlint.plugin)
    alias(libs.plugins.screenshot)
}

@Suppress("UnstableApiUsage")
android {
    compileSdk = 34
    buildFeatures {
        compose = true
    }
    buildTypes {
        named("release") {
            isMinifyEnabled = false
            setProguardFiles(listOf(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"))
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_17)
        targetCompatibility(JavaVersion.VERSION_17)
        isCoreLibraryDesugaringEnabled = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    defaultConfig {
        applicationId = "com.vitantonio.nagauzzi.unusedappfinder"
        minSdk = 22
        // FIXME: failed to configure test_UnusedAppStatelessList: Package targetSdkVersion=34 > maxSdkVersion=33
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    experimentalProperties["android.experimental.enableScreenshotTest"] = true
    kotlinOptions {
        jvmTarget = "17"
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    coreLibraryDesugaring(libs.desugar)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(libs.bundles.accompanist)
    implementation(libs.androidx.activity.compose)
    implementation(libs.bundles.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.viewModel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.gms.play.services.oss.licenses)
    implementation(libs.hilt.android)
    implementation(libs.kotlin.stdlib)
    kapt(libs.hilt.compiler)
    screenshotTestImplementation(libs.androidx.compose.ui.tooling)
    testImplementation(libs.androidx.compose.ui.test.junit4)
    testImplementation(libs.junit4)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.robolectric)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    implementation(libs.androidx.compose.ui.test.manifest)
}
android {
    namespace = "com.vitantonio.nagauzzi.unusedappfinder"
}

kapt {
    correctErrorTypes = true
}
