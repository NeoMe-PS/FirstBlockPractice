plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.psbn.firstblockpractice.core"
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
val rxAndroidVersion = "3.0.2"
val rxJavaVersion = "3.1.5"
val rxBindingVersion = "4.0.0"
val splashScreenVersion = "1.0.1"
val lifecycleVersion = "2.7.0"
val coroutinesAndroidVersion = "1.6.4"
val coroutinesCoreVersion = "1.4.2"
val httpInterceptorVersion = "4.12.0"
val glideVersion = "4.16.0"
val roomVersion = "2.6.1"
val daggerVersion = "2.51.1"


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
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.retrofit2:adapter-rxjava3:$retrofitVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$httpInterceptorVersion")


    //FragmentKTX
    implementation("androidx.fragment:fragment-ktx:$fragmentKtxVersion")

    //faker - for generates fake data
    implementation("com.github.javafaker:javafaker:$javaFakerVersion")

    // kotlinx-datetime
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0-RC.2")

    // Preference
    implementation("androidx.preference:preference-ktx:$preferenceVersion")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:$dataStoreVersion")
    implementation("androidx.datastore:datastore:$dataStoreVersion")

    // Gson
    implementation("com.google.code.gson:gson:$gsonVersion")

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

    //NavComponent
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    //Coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesCoreVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesAndroidVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    //Glide
    implementation("com.github.bumptech.glide:glide:$glideVersion")

    //Room
    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")

    //Dagger
    implementation("com.google.dagger:dagger:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")
}
