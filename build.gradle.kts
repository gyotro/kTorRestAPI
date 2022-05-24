import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType.Companion.attribute

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val koin_version: String by project

plugins {
    application
    kotlin("jvm") version "1.6.21"
                id("org.jetbrains.kotlin.plugin.serialization") version "1.6.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.example"
version = "0.0.1"
application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = false
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

/*shadowJar {
    archiveFileName = "app.jar"
    manifest {
        attribute = 'Main-Class' = mainClass
    }
}*/


repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    // https://mvnrepository.com/artifact/com.rabbitmq/amqp-client
    implementation("com.rabbitmq:amqp-client:5.14.2")
    implementation("com.github.JUtupe:ktor-rabbitmq:0.4.0")
    implementation("io.ktor:ktor-server-resources:$ktor_version")
    implementation("io.ktor:ktor-server-auth:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-call-logging-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-default-headers-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    // Koin for Ktor
    implementation("io.insert-koin:koin-ktor:$koin_version")
    // SLF4J Logger
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")
    // Apache Http Client
    implementation("io.ktor:ktor-client-apache:$ktor_version")
    // https://mvnrepository.com/artifact/io.ktor/ktor-jackson
    implementation("io.ktor:ktor-jackson:1.6.8")
}