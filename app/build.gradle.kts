plugins {
    kotlin("kapt")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android-extensions")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.dagger.hilt.android")
    //   id("com.google.firebase.crashlytics")
}
android {
    namespace = "com.aymen.testapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.aymen.testapp"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"


        testInstrumentationRunner ="androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
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
            resources.excludes.add("META-INF/LICENSE.md")
            resources.excludes.add("META-INF/LICENSE-notice.md")
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.compose.material3:material3:1.1.0-alpha03")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.7.0")

    implementation("androidx.constraintlayout:constraintlayout:2.1.4")


    implementation(project(mapOf("path" to ":framework")))
    implementation(project(mapOf("path" to ":core")))

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.44.2")
    kapt("com.google.dagger:hilt-android-compiler:2.44.2")

    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:31.0.0"))

    // Declare the dependency for the Crashlytics library
    //implementation ("com.google.firebase:firebase-crashlytics-ktx")

    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation("org.mockito:mockito-core:4.10.0")
    androidTestImplementation("org.mockito:mockito-android:4.0.0")

    // Picasso
    implementation("com.squareup.picasso:picasso:2.71828")


    //Architecture Components - Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0-alpha04")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0-alpha04")

    //Paging
    val pagingVersion = "3.1.1"
    implementation("androidx.paging:paging-runtime:$pagingVersion")
    implementation ("androidx.paging:paging-compose:1.0.0-alpha17")

    //For runBlockingTest, CoroutineDispatcher etc.
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    // For Robolectric tests.
    testImplementation("com.google.dagger:hilt-android-testing:2.44.2")
    // ...with Kotlin.
    kaptTest("com.google.dagger:hilt-android-compiler:2.44.2")
    // For instrumented tests.
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.44.2")
    // ...with Kotlin.
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.44.2")

    //mockk
    testImplementation("io.mockk:mockk:1.13.3")
    androidTestImplementation("io.mockk:mockk:1.13.3")

    testImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")

    androidTestImplementation("androidx.navigation:navigation-testing:2.5.3")

    androidTestImplementation("com.google.truth:truth:1.1.3")
}