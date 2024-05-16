plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.psbn.firstblockpractice.auth"
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
    }
}

val daggerVersion = "2.51.1"
val rxAndroidVersion = "3.0.2"
val rxJavaVersion = "3.1.5"
val rxBindingVersion = "4.0.0"
dependencies {

    implementation(project(":core"))

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.navigation:navigation-common-ktx:2.7.7")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Dagger
    implementation("com.google.dagger:dagger:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")

    //RxJava
    implementation("io.reactivex.rxjava3:rxandroid:$rxAndroidVersion")
    implementation("io.reactivex.rxjava3:rxjava:$rxJavaVersion")
    //RxBinding
    implementation("com.jakewharton.rxbinding4:rxbinding:$rxBindingVersion")
    implementation("com.jakewharton.rxbinding4:rxbinding-core:$rxBindingVersion")
    implementation("com.jakewharton.rxbinding4:rxbinding-appcompat:$rxBindingVersion")
    implementation("com.jakewharton.rxbinding4:rxbinding-drawerlayout:$rxBindingVersion")
    implementation("com.jakewharton.rxbinding4:rxbinding-leanback:$rxBindingVersion")
    implementation("com.jakewharton.rxbinding4:rxbinding-recyclerview:$rxBindingVersion")
    implementation("com.jakewharton.rxbinding4:rxbinding-slidingpanelayout:$rxBindingVersion")
    implementation("com.jakewharton.rxbinding4:rxbinding-swiperefreshlayout:$rxBindingVersion")
    implementation("com.jakewharton.rxbinding4:rxbinding-viewpager:$rxBindingVersion")
    implementation("com.jakewharton.rxbinding4:rxbinding-viewpager2:$rxBindingVersion")
    implementation("com.jakewharton.rxbinding4:rxbinding-material:$rxBindingVersion")
}