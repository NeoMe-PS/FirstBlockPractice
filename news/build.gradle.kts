plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.psbn.news"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }
}
val coreKtxVersion = "1.12.0"
val appcompatVersion = "1.6.1"
val materialVersion = "1.11.0"
val constraintlayoutVersion = "2.1.4"
val fragmentKtxVersion = "1.6.2"

val junitVersion = "4.13.2"
val testJunitVersion = "1.1.5"
val testEspressoVersion = "3.5.1"
val composeMaterialVersion = "1.2.1"

val lifecycleVersion = "2.7.0"
val coroutinesAndroidVersion = "1.6.4"
val coroutinesCoreVersion = "1.4.2"
val daggerVersion = "2.51.1"
val navVersion = "2.7.7"
val retrofitVersion = "2.9.0"
val httpInterceptorVersion = "4.12.0"
val composeViewModelVersion = "2.8.0"
dependencies {

    implementation(project(":core"))

    implementation("androidx.core:core-ktx:$coreKtxVersion")
    implementation("androidx.appcompat:appcompat:$appcompatVersion")
    implementation("com.google.android.material:material:$materialVersion")
    implementation("androidx.constraintlayout:constraintlayout:$constraintlayoutVersion")
    implementation("androidx.compose.material3:material3-android:$composeMaterialVersion")
    testImplementation("junit:junit:$junitVersion")
    androidTestImplementation("androidx.test.ext:junit:$testJunitVersion")
    androidTestImplementation("androidx.test.espresso:espresso-core:$testEspressoVersion")

    //NavComponent
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    //Coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesCoreVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesAndroidVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    //Dagger
    implementation("com.google.dagger:dagger:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.retrofit2:adapter-rxjava3:$retrofitVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$httpInterceptorVersion")

    //FragmentKTX
    implementation("androidx.fragment:fragment-ktx:$fragmentKtxVersion")

    // kotlinx-datetime
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0-RC.2")

    // Compose
    val composeBom = platform("androidx.compose:compose-bom:2024.05.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.compose.material3:material3-window-size-class")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$composeViewModelVersion")
    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material:material-icons-extended")
}