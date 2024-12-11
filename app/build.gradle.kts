plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.finalproject"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.finalproject"
        minSdk = 27
        targetSdk = 33
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
}


    dependencies {
        implementation("androidx.room:room-common:2.6.1")
        implementation ("com.squareup.retrofit2:retrofit:2.9.0")
        implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
        implementation ("androidx.room:room-runtime:2.5.1")
        implementation ("androidx.room:room-ktx:2.5.1")
        implementation ("com.google.android.material:material:1.9.0")
        implementation ("androidx.appcompat:appcompat:1.6.1")
        implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
        implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
        implementation ("androidx.navigation:navigation-fragment-ktx:2.6.0")
        implementation ("androidx.navigation:navigation-ui-ktx:2.6.0")
    }



