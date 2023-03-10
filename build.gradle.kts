buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        classpath("com.android.tools.build:gradle:7.2.2")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44.2")
        classpath("com.google.gms:google-services:4.3.14")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.6.0-alpha04")
        // Add the Crashlytics Gradle plugin
        //classpath ("com.google.firebase:firebase-crashlytics-gradle:2.9.2")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}