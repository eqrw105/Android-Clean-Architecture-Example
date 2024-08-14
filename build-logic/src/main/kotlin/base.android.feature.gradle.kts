import com.smin.base.configureComposeAndroid
import com.smin.base.configureHiltAndroid
import com.smin.base.configureKotlinAndroid
import com.smin.base.libs

plugins {
    id("base.android.library")
    id("base.android.compose")
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

configureKotlinAndroid()
configureHiltAndroid()
configureComposeAndroid()

dependencies {
    val libs = project.extensions.libs
    implementation(project(":core:designsystem"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))

    implementation(libs.findLibrary("androidx.core.ktx").get())
    implementation(libs.findLibrary("androidx.lifecycle.runtime.ktx").get())

    testImplementation(libs.findLibrary("junit").get())
    androidTestImplementation(libs.findLibrary("androidx.junit").get())
    androidTestImplementation(libs.findLibrary("androidx.espresso.core").get())
}