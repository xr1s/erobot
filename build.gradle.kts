import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile

plugins {
  val kotlinVersion = "1.4.21"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    application
}

repositories {
    jcenter()
    mavenCentral()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
}

tasks.withType(KotlinJvmCompile::class.java) {
  kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    val miraiVersion = "2.0.0"
    api("net.mamoe:mirai-core-all:$miraiVersion")

    val kotlinx_serialization_version = "1.0.1"
    val kotlinx_coroutines_version = "1.4.2"
    val ktor_version = "1.5.0"

    api(kotlin("stdlib"))
    api(kotlin("reflect"))

    api("org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinx_serialization_version")

    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinx_coroutines_version")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$kotlinx_coroutines_version")

    api("io.ktor:ktor-client-core:$ktor_version")
    api("io.ktor:ktor-client-okhttp:$ktor_version")
    api("io.ktor:ktor-client-serialization:$ktor_version")

    api("org.bytedeco:javacv-platform:1.5.4")
}

application {
  mainClass.set("me.xr1s.erobot.ErobotKt")
}
