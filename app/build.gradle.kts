plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.quizio"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.quizio"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    // Enable View Binding (optional, helps cleaner code)
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    // AndroidX Core Components
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Firebase (Auth + Database)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database)

    // Google Sign-In / Credentials API
    implementation(libs.credentials)
    implementation(libs.credentials.play.services.auth)
    implementation(libs.googleid)

    // Gson for JSON storage (custom questions)
    implementation("com.google.code.gson:gson:2.10.1")
    implementation(libs.androidx.core)

    // Testing - JUnit (Unit tests)
    testImplementation(libs.junit)

    // Mockito for Unit Testing
    testImplementation("org.mockito:mockito-core:5.11.0")

    // Instrumentation Testing (Android)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Mockito for Android Instrumentation Tests
    androidTestImplementation("org.mockito:mockito-android:5.11.0")

    // AndroidX Test Core (needed for ApplicationProvider & Context testing)
    androidTestImplementation("androidx.test:core:1.5.0")
    androidTestImplementation(project(":app"))
    androidTestImplementation(project(":app"))
    androidTestImplementation(project(":app"))
    androidTestImplementation(project(":app"))
}
