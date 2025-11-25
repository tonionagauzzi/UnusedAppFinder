import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.application")
    id("com.google.android.gms.oss-licenses-plugin")
    id("com.google.dagger.hilt.android")
    id("kotlin-android")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp.gradle.plugin)
    alias(libs.plugins.ktlint.plugin)
    alias(libs.plugins.screenshot)
}

@Suppress("UnstableApiUsage")
android {
    compileSdk = 36
    buildFeatures {
        buildConfig = true
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    defaultConfig {
        applicationId = "com.vitantonio.nagauzzi.unusedappfinder"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    experimentalProperties["android.experimental.enableScreenshotTest"] = true
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(libs.bundles.accompanist)
    implementation(libs.androidx.activity.compose)
    implementation(libs.bundles.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.viewModel.compose)
    implementation(libs.gms.play.services.oss.licenses)
    implementation(libs.hilt.android)
    implementation(libs.kotlin.stdlib)
    ksp(libs.hilt.compiler)
    screenshotTestImplementation(libs.androidx.compose.ui.tooling)
    testImplementation(libs.androidx.compose.ui.test.junit4)
    testImplementation(libs.junit4)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.robolectric)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    implementation(libs.androidx.compose.ui.test.manifest)
}
android {
    namespace = "com.vitantonio.nagauzzi.unusedappfinder"
}

tasks.withType<Test> {
    if (name.contains("ScreenshotTest")) {
        failOnNoDiscoveredTests = false
    }
}
