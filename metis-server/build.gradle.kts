plugins {
    java
    id("org.springframework.boot") version "3.5.3"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "dk.michaelbui"
version = "0.0.1-SNAPSHOT"
val mainClass = "dk.michaelbui.metis.server.MetisServerApplication"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":metis-core"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation(libs.springdoc)

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.bootJar {
    archiveFileName.set("metis-server.jar")
}

tasks.jar {
    enabled = false
    manifest.attributes["Main-Class"] = mainClass
    val dependencies = configurations.runtimeClasspath
        .get()
        .map { if (it.isDirectory) it else zipTree(it) }
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.withType<Test> {
    useJUnitPlatform()
}

