plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android") // Correct Kotlin plugin syntax
    id("dev.flutter.flutter-gradle-plugin")
    id("com.google.gms.google-services") // Google services plugin
}

android {
    namespace = "com.example.recipe_app"
    compileSdk = 34 // Use an explicit version instead of flutter.compileSdkVersion
    ndkVersion = "27.0.12077973" // Set required NDK version

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    defaultConfig {
        applicationId = "com.example.recipe_app"
        minSdk = 21 // Minimum SDK version
        targetSdk = 34 // Explicit target SDK version
        versionCode = 1
        versionName = "1.0"
    }

    signingConfigs {
        create("release") {
            storeFile = file("keystore.jks") // Provide a valid keystore path
            storePassword = "your-store-password"
            keyAlias = "your-key-alias"
            keyPassword = "your-key-password"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false // ✅ Fixed in Kotlin DSL
            isShrinkResources = false // ✅ Fixed in Kotlin DSL
            signingConfig = signingConfigs.getByName("release") // ✅ Use the correct signing config
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

flutter {
    source = "../.."
}
