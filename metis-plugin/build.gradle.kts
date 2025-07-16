plugins {
    id("java")
}

group = "dk.michaelbui"
version = "unspecified"

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

    implementation(libs.pf4j)
    implementation(libs.jackson.dataformat.yaml)
    implementation(libs.jackson.databind)

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}