plugins {
    java
    id("org.springframework.boot") version "3.5.3"
    id("io.spring.dependency-management") version "1.1.7"
    antlr
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
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation(libs.jackson.databind)
    implementation(libs.springdoc)

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    antlr(libs.antlr)
}

tasks.jar {
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

tasks.named("build"){
    dependsOn("generateGrammarSource")
}

tasks.generateGrammarSource {
    arguments = arguments + listOf("-visitor", "-long-messages")
}

