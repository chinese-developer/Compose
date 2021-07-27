
buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        google()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.2.2")
//        classpath("com.google.dagger:hilt-android-gradle-plugin:2.38")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Dependencies.Kotlin.version}")
    }
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        google()
        maven(url = "https://jitpack.io")
    }
}