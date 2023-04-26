plugins {
    id("com.android.application")
    id("com.google.android.gms.oss-licenses-plugin")
    id("com.google.dagger.hilt.android")
    id("kotlin-android")
    id("kotlin-kapt")
}

@Suppress("UnstableApiUsage")
android {
    compileSdk = 33
    defaultConfig {
        applicationId = "com.vitantonio.nagauzzi.unusedappfinder"
        minSdk = 22
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    buildTypes {
        named("release") {
            isMinifyEnabled = false
            setProguardFiles(listOf(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"))
        }
    }
}

dependencies {
    // TODO: The following code may not be necessary.
    // Add missing dependencies for JDK 9+
    if (JavaVersion.current() >= JavaVersion.VERSION_1_9) {
        annotationProcessor("javax.xml.bind:jaxb-api:2.3.1")
        annotationProcessor("com.sun.xml.bind:jaxb-core:2.3.0.1")
        annotationProcessor("com.sun.xml.bind:jaxb-impl:2.3.2")
        kapt("com.sun.xml.bind:jaxb-core:2.3.0.1")
        kapt("javax.xml.bind:jaxb-api:2.3.1")
        kapt("com.sun.xml.bind:jaxb-impl:2.3.2")
    }

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
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.espresso)
    androidTestImplementation(libs.androidx.test.runner)
}

kapt {
    correctErrorTypes = true
}
