plugins {
    alias(libs.plugins.kotlin.plain)
    alias(libs.plugins.kotlin.serialization)
    application
}

group = "com.example.hi_quran"
version = "1.0-SNAPSHOT"

dependencies {
    // Retrofit & Networking
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-kotlinx-serialization:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    
    // Serialization
    implementation(libs.kotlinx.serialization.json)
    
    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    
    // SQLite JDBC
    implementation("org.xerial:sqlite-jdbc:3.46.0.0")
    
    // Testing
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("com.example.hi_quran.generator.MainKt")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}
