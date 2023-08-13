// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath (Libs.Gradle.androidGradlePlugin)
        classpath (Libs.Google.googleServices)
        classpath (Libs.AndroidX.navSafeArgPlug)
    }
}
allprojects{
    repositories{
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

plugins {
    id ("com.android.application") version "8.1.0" apply false
    id ("com.android.library") version "8.1.0" apply false
    id ("org.jetbrains.kotlin.android") version "1.7.20" apply false
    id ("com.google.dagger.hilt.android") version "2.44" apply false
    id ("org.jetbrains.kotlin.kapt") version "1.8.0"
}

tasks.register("clean",Delete::class) {
    delete(rootProject.buildDir)
}   