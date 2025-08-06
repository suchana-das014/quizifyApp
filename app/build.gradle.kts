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

    // View Binding for easier UI handling
    buildFeatures {
        viewBinding = true
    }

    // Ensure JUnit 5 works for local tests
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {

    // --- Core AndroidX & Material Components ---
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.androidx.core)

    // --- Firebase ---
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database)

    // --- Google Sign-In / Credentials API ---
    implementation(libs.credentials)
    implementation(libs.credentials.play.services.auth)
    implementation(libs.googleid)

    // --- JSON (Gson) ---
    implementation("com.google.code.gson:gson:2.10.1")

    // ========================
    // TESTING DEPENDENCIES
    // ========================

    // --- JUnit 5 (Local Unit Tests) ---
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.2") // For @CsvFileSource

    // --- Mockito (Local Unit Tests) ---
    testImplementation("org.mockito:mockito-core:5.11.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.11.0")

    // --- Android Instrumentation Tests (JUnit4/Espresso) ---
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // --- Mockito for Android Instrumentation ---
    androidTestImplementation("org.mockito:mockito-android:5.11.0")

    // --- AndroidX Test Core ---
    androidTestImplementation("androidx.test:core:1.5.0")
}

// Use JUnit Platform for running tests
tasks.withType<Test> {
    useJUnitPlatform()
}
