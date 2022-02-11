import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.10"
    id("de.solugo.gitversion") version "1.0.0"
    id("org.gradle.application")
}

group = "de.solugo.rpsgame"
version = gitVersion.version()

repositories {
    mavenCentral()
}

dependencies {
    val jupiterVersion = "5.8.2"

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

application {
    mainClass.set("RpsGame")
}