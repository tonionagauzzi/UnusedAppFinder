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
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation("androidx.compose.material:material:1.4.2")
    implementation("androidx.compose.ui:ui:1.4.2")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.2")
    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation("com.google.accompanist:accompanist-drawablepainter:0.25.1")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.25.1")
    implementation("com.google.android.gms:play-services-oss-licenses:17.0.1")
    implementation("com.google.dagger:hilt-android:2.45")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.0")
    kapt("com.google.dagger:hilt-compiler:2.45")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

kapt {
    correctErrorTypes = true
}
