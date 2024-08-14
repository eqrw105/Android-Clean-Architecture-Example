plugins {
    id ("base.android.library")
}

android {
    namespace = "com.smin.core.data"
}

dependencies {
    implementation(projects.core.domain)
    testImplementation(libs.junit)
}