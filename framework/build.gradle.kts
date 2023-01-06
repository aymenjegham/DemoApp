plugins {
    kotlin("kapt")
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
   // id("com.google.gms.google-services")
   // id("com.google.firebase.crashlytics")

}

android {
    namespace ="com.aymen.framework"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33


        testInstrumentationRunner = "com.aymen.framework.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isJniDebuggable = true
            isMinifyEnabled =false
            buildConfigField("String", "BASE_URL", "\"https://randomuser.me/api/\"")
        }

        release {
            isMinifyEnabled =  true
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "BASE_URL", "\"https://randomuser.me/api/\"")
        }
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packagingOptions {
        resources {
            resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(project(mapOf("path" to ":core")))
    androidTestImplementation(project(mapOf("path" to ":framework")))

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("com.google.firebase:firebase-auth:21.1.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.44.2")
    androidTestImplementation(project(mapOf("path" to ":framework")))
    androidTestImplementation(project(mapOf("path" to ":framework")))

    kapt("com.google.dagger:hilt-android-compiler:2.44.2")

    //Room
    val roomVersion = "2.4.3"

    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    testImplementation("androidx.room:room-testing:$roomVersion")
    implementation("androidx.room:room-paging:$roomVersion")

    // Picasso
    implementation("com.squareup.picasso:picasso:2.71828")


    //Gson
    implementation("com.google.code.gson:gson:2.9.0")

    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:31.0.0"))

    //Retrofit2
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp3 logging-interceptor
    implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")

    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.3")

    // For instrumented tests.
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.44.2")
    // ...with Kotlin.
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.44.2")

    androidTestImplementation("com.google.truth:truth:1.1.3")

    // Declare the dependency for the Crashlytics library
 //   implementation ("com.google.firebase:firebase-crashlytics-ktx")
}

kapt {
    correctErrorTypes = true
}