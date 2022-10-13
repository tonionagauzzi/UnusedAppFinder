plugins {
    id("com.android.application")
    id("com.cookpad.android.licensetools")
    id("com.google.dagger.hilt.android")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(31)
    defaultConfig {
        applicationId = "com.vitantonio.nagauzzi.unusedappfinder"
        minSdkVersion(22)
        targetSdkVersion(31)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    buildTypes {
        named("release") {
            isMinifyEnabled = false
            setProguardFiles(listOf(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"))
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
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
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.5.30")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.2")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-compiler:2.44")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}

licenseTools {
    ignoredGroups = setOf(
        "androidx.activity",
        "androidx.annotation",
        "androidx.appcompat",
        "androidx.arch.core",
        "androidx.asynclayoutinflater",
        "androidx.collection",
        "androidx.concurrent",
        "androidx.constraintlayout",
        "androidx.coordinatorlayout",
        "androidx.cursoradapter",
        "androidx.customview",
        "androidx.databinding",
        "androidx.documentfile",
        "androidx.drawerlayout",
        "androidx.emoji2",
        "androidx.fragment",
        "androidx.interpolator",
        "androidx.legacy",
        "androidx.loader",
        "androidx.lifecycle",
        "androidx.localbroadcastmanager",
        "androidx.media",
        "androidx.navigation",
        "androidx.print",
        "androidx.recyclerview",
        "androidx.resourceinspection",
        "androidx.savedstate",
        "androidx.slidingpanelayout",
        "androidx.startup",
        "androidx.swiperefreshlayout",
        "androidx.tracing",
        "androidx.transition",
        "androidx.vectordrawable",
        "androidx.versionedparcelable",
        "androidx.viewpager",
        "androidx.window",
        "com.google.code.findbugs",
        "com.google.guava",
        "org.jetbrains",
        "org.jetbrains.kotlinx"
    )
}

kapt {
    correctErrorTypes = true
}
