object Libs {

    object Gradle {
        const val androidGradlePlugin = "com.android.tools.build:gradle:" + Versions.gradleVersion
    }

    object TestLibs{
        const val junit = "junit:junit:" + Versions.jUnitVersion
        const val junitExtTest = "androidx.test.ext:junit:" + Versions.junitExtTestVersion
        const val espresso = "androidx.test.espresso:espresso-core:" + Versions.espressoVersion
    }

    object AndroidX {
        const val core = "androidx.core:core-ktx:" + Versions.coreKtxVersion
        const val appCompat = "androidx.appcompat:appcompat:" + Versions.appCompatVersion
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:" + Versions.constraintLayoutVersion

        const val vmLifeCycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:" + Versions.lifecycleVersion
        const val liveCycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:" + Versions.lifecycleVersion
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:" + Versions.lifecycleVersion
        const val lifeCycleExtension = "androidx.lifecycle:lifecycle-extensions:" + Versions.lifecycleExtensionsVersion

        const val navigation = "androidx.navigation:navigation-fragment-ktx:" + Versions.navigationVersion
        const val navigationUI = "androidx.navigation:navigation-ui-ktx:" + Versions.navigationVersion
        const val navSafeArgPlug = "androidx.navigation:navigation-safe-args-gradle-plugin:" + Versions.navSafeArgPlugVersion

        const val encryptedSharedPref = "androidx.security:security-crypto:" + Versions.encryptedSharedPref
    }

    object Google {
        const val material = "com.google.android.material:material:" + Versions.materialVersion

        const val googleServices = "com.google.gms:google-services:" + Versions.googleServicesVersion
    }
    object NetworkLib{
        const val retrofit = "com.squareup.retrofit2:retrofit:"+ Versions.retrofitVersion
        const val gsonConv = "com.squareup.retrofit2:converter-gson:" + Versions.retrofitVersion
        const val okhttp =  "com.squareup.okhttp3:logging-interceptor:" + Versions.okHttpVersion
    }

    object Hilt {
        const val android = "com.google.dagger:hilt-android:" + Versions.hiltVersion
        const val compiler = "com.google.dagger:hilt-compiler:" + Versions.hiltCompilerVersion
        const val plugin = "com.google.dagger:hilt-android-gradle-plugin:" + Versions.hiltVersion
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:" + Versions.roomVersion
        const val compiler = "androidx.room:room-compiler:" + Versions.roomVersion
        const val ktx = "androidx.room:room-ktx:" + Versions.roomVersion
    }

    object Coroutines {
        const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:" + Versions.coroutinesVersion
        const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:" + Versions.coroutinesVersion
    }

    object Glide{
        const val glide = "com.github.bumptech.glide:glide:" + Versions.glideVersion
        const val glideCompailer = "com.github.bumptech.glide:compiler:" + Versions.glideVersion
    }
}