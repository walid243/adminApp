plugins {
    kotlin("jvm") version "1.8.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.mongodb:mongodb-driver-sync:4.2.3")
    implementation("org.mongodb:bson:4.2.3")
    implementation("org.mongodb:mongodb-driver-reactivestreams:4.2.3")
    implementation("org.litote.kmongo:kmongo:4.3.0")
    implementation("org.slf4j:slf4j-api:1.7.32")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}