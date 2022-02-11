import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.10"
    id("de.solugo.gitversion") version "1.0.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.gradle.application")
}

group = "de.solugo.rpsgame"
version = gitVersion.version(tagPrefix = "v")

repositories {
    mavenCentral()
}

dependencies {
    val jupiterVersion = "5.8.2"

    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.4")

    testImplementation("org.junit.jupiter:junit-jupiter-api:$jupiterVersion")
    testImplementation("io.strikt:strikt-jvm:0.34.1")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jupiterVersion")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.shadowJar {
    archiveClassifier.set("")
}

application {
    mainClass.set("RpsGame")
}

