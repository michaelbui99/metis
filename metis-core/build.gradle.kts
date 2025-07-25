plugins {
    id("java")
    antlr
}

group = "dk.michaelbui"
version = "unspecified"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencies {
    implementation(libs.jackson.databind)
    implementation(libs.spring.logging)

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    antlr(libs.antlr)
}

tasks.test {
    useJUnitPlatform()
}

tasks.generateGrammarSource {
    arguments = arguments + listOf("-visitor", "-long-messages")
}

tasks.named("build"){
    dependsOn("generateGrammarSource")
}

