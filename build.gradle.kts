// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.2.2")
        classpath("com.google.android.gms:oss-licenses-plugin:0.10.5")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

plugins {
    id("com.google.dagger.hilt.android") version "2.44" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter().mavenContent {
            includeGroup("com.cookpad.android.licensetools")
        }
    }
}

tasks.register<Delete>("clean").configure {
    delete(rootProject.buildDir)
}