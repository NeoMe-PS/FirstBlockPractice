plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.ps_pn.firstblockpractice"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ps_pn.firstblockpractice"
        minSdk = 26
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
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
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

val retrofitVersion = "2.9.0"
val navVersion = "2.7.7"
val splashVersion = "1.0.1"
val javaFakerVersion = "1.0.2"
val desugaringVersion = "2.0.3"
val dataStoreVersion = "1.0.0"
val gsonVersion = "2.10.1"
val preferenceVersion = "1.2.1"

dependencies {

    implementation("androidx.core:core-ktx:$coreKtxVersion")
    implementation("androidx.appcompat:appcompat:$appcompatVersion")
    implementation("com.google.android.material:material:$materialVersion")
    implementation("androidx.constraintlayout:constraintlayout:$constraintlayoutVersion")
    testImplementation("junit:junit:$junitVersion")
    androidTestImplementation("androidx.test.ext:junit:$testJunitVersion")
    androidTestImplementation("androidx.test.espresso:espresso-core:$testEspressoVersion")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")

    //FragmentKTX
    implementation("androidx.fragment:fragment-ktx:$fragmentKtxVersion")

    //faker - for generates fake data
    implementation("com.github.javafaker:javafaker:$javaFakerVersion")

    // kotlinx-datetime
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0-RC.2")

    // Java 8+ API desugaring support
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:$desugaringVersion")

    // Preference
    implementation("androidx.preference:preference-ktx:$preferenceVersion")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:$dataStoreVersion")
    implementation("androidx.datastore:datastore:$dataStoreVersion")

    // Gson
    implementation ("com.google.code.gson:gson:$gsonVersion")
}
