package com.smin.base

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureComposeAndroid() {
    val libs = extensions.libs

    androidExtension.apply {
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = "1.5.14"
        }

        dependencies {
            val bom = libs.findLibrary("androidx.compose.bom").get()
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))

            add("implementation", libs.findLibrary("androidx.activity.compose").get())
            add("implementation", libs.findLibrary("androidx.ui").get())
            add("implementation", libs.findLibrary("androidx.ui.graphics").get())
            add("implementation", libs.findLibrary("androidx.ui.tooling.preview").get())
            add("implementation", libs.findLibrary("androidx.material3").get())
            add("implementation", libs.findLibrary("androidx.lifecycle.runtime.compose.android").get())
        }
    }
}