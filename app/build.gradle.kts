plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("com.google.gms.google-services")
    id ("dagger.hilt.android.plugin")
    id ("kotlin-kapt")
    id ("kotlin-parcelize")
}

android {
    namespace = "com.rabbithole.firebasenotebook"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rabbithole.firebasenotebook"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
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
    buildFeatures{
        viewBinding = true
    }
    packagingOptions {
        pickFirst ("META-INF/gradle/incremental.annotation.processors")
    }
}

dependencies {

    implementation ("androidx.core:core-ktx:1.7.0")
    implementation ("androidx.appcompat:appcompat:1.4.1")
    implementation ("com.google.android.material:material:1.6.0")
    implementation ("androidx.viewpager2:viewpager2:1.1.0-beta01")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.3")

    //Jetpack Navigation Component
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.4.2")
    implementation ("androidx.navigation:navigation-ui-ktx:2.4.2")

    // LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    //fragment
    implementation ("androidx.fragment:fragment-ktx:1.4.1")

    //Dagger Hilt
    implementation ("com.google.dagger:hilt-android:2.40.5")
    kapt ("com.google.dagger:hilt-android-compiler:2.40.5")
    implementation ("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    kapt ("androidx.hilt:hilt-compiler:1.0.0")

    //Testing
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")

    //Firebase
    implementation ("com.google.firebase:firebase-firestore:24.1.2")
    implementation ("com.google.firebase:firebase-auth:19.2.0")
    implementation ("com.google.firebase:firebase-storage-ktx:20.0.1")
    implementation ("com.google.firebase:firebase-database-ktx:20.0.5")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.3.1")

    //Gson
    implementation ("com.google.code.gson:gson:2.8.9")
}