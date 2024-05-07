plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")  // Changed from annotationProcessor to kapt
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.jokeapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.jokeapp"
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
    buildFeatures{
        dataBinding = true
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

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.tracing:tracing-perfetto-handshake:1.0.0")
    implementation("org.chromium.net:cronet-embedded:119.6045.31")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("com.google.android.datatransport:transport-runtime:3.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // retrofit 
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation ("com.squareup.okhttp3:okhttp:4.12.0")

    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    implementation ("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    implementation ("com.jakewharton.timber:timber:5.0.1")
    implementation ("com.facebook.stetho:stetho:1.6.0")
    implementation ("com.orhanobut:logger:2.2.0")
    implementation ("ch.qos.logback:logback-classic:1.2.6")

    implementation ("com.shakebugs:shake:16.2.5")

    implementation ("com.squareup.okhttp3:okhttp:4.12.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    //http logcat
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.7.0")
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    // Kotlinx Serialization dependency
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    //Editext
    implementation("com.google.android.material:material:1.11.0")

    //noinspection KaptUsageInsteadOfKsp
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    implementation("it.xabaras.android:recyclerview-swipedecorator:1.4")

    implementation ("com.github.krushang06:okhttp:1.0.0")

}