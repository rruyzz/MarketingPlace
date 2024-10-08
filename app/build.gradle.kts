plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.meli.marketingplace"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.meli.marketingplace"
        minSdk = 24
        targetSdk = 34
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.koinCore)
    implementation(libs.koinAndroid)
    implementation(project(LocalLibs.network))
    implementation(project(LocalLibs.navigation))
    implementation(project(LocalLibs.common))
    implementation(project(LocalLibs.searchPresentation))
    implementation(project(LocalLibs.searchDomain))
    implementation(project(LocalLibs.searchData))
    implementation(project(LocalLibs.productListPresentation))
    implementation(project(LocalLibs.productListDomain))
    implementation(project(LocalLibs.productListData))
    implementation(project(LocalLibs.productDetailPresentation))
    implementation(project(LocalLibs.productDetailDomain))
    implementation(project(LocalLibs.productDetailData))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}