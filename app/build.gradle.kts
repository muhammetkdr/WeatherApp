import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-parcelize")
    id ("com.google.dagger.hilt.android")
    id ("androidx.navigation.safeargs.kotlin")
    id ("kotlin-kapt")
}

android {
    namespace = Config.applicationId
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Config.testInstRunner
        vectorDrawables {
            useSupportLibrary = true
        }

        val apiKey = gradleLocalProperties(rootDir).getProperty("API_KEY")
        buildConfigField("String", "API_KEY", apiKey)

    }

    buildTypes {
        debug {
            isDebuggable = true
            isJniDebuggable = true
            isRenderscriptDebuggable = true
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures{
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    implementation (Libs.AndroidX.core)
    implementation (Libs.AndroidX.appCompat)
    implementation (Libs.Google.material)
    implementation (Libs.AndroidX.constraintLayout)
    implementation(Libs.AndroidX.legacySupportLib)
    implementation (Libs.AndroidX.liveData)
    implementation (Libs.AndroidX.vmLifeCycle)

    //test
    testImplementation (Libs.TestLibs.junit)
    implementation("androidx.test.ext:junit-ktx:1.1.5")

    // MockWeb Server
    testImplementation("com.squareup.okhttp3:mockwebserver:4.10.0")

    // Mockito
    testImplementation ("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation ("org.mockito:mockito-inline:3.0.0")

    // Truth
    testImplementation ("com.google.truth:truth:1.1.3")

    // Turbine
    testImplementation ("app.cash.turbine:turbine:0.12.1")

    // Coroutines-Test
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")

    // Core-testing
    implementation ("androidx.arch.core:core-testing:2.2.0")
    implementation ("androidx.test:core-ktx:1.5.0")

    // Coroutines
    implementation (Libs.Coroutines.coroutineCore)
    implementation (Libs.Coroutines.coroutineAndroid)

    // Lifecycle Scopes
    implementation (Libs.AndroidX.liveCycleRuntime)
    implementation (Libs.AndroidX.lifeCycleExtension)

    // Retrofit
    implementation (Libs.NetworkLib.retrofit)
    implementation (Libs.NetworkLib.gsonConv)
    //okhttp
    implementation (Libs.NetworkLib.okhttp)

    // Navigation Components
    implementation (Libs.AndroidX.navigation)
    implementation (Libs.AndroidX.navigationUI)

    // Glide
    implementation (Libs.Glide.glide)
    kapt (Libs.Glide.glideCompailer)

    //Dagger-hilt
    implementation (Libs.Hilt.android)
    kapt (Libs.Hilt.compiler)
    implementation(Libs.Hilt.plugin)

    //Encrypted SharedPref
    implementation (Libs.AndroidX.encryptedSharedPref)

    // Google location service
    implementation (Libs.Google.googleLocationService)

    // MP chart
    implementation (Libs.ThirdPartyLib.mpChartLib)

    //chucker
    debugImplementation (Libs.ThirdPartyLib.chuckerDebugLib)
}