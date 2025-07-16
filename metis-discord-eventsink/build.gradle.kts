plugins {
    id("java")
}

group = "dk.michaelbui.metis"
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
    implementation(project(":metis-plugin"))

    implementation(libs.pf4j)
    implementation(libs.jackson.databind)
    implementation(libs.jackson.datatype.jsr310)

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    annotationProcessor(libs.pf4j)
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar{
    manifest{
        attributes(
            "Plugin-Id" to "metis-eventsink-builtin-discord",
            "Plugin-Version" to "0.1.0",
            "Plugin-Provider" to "Michael Bui"
        )
    }
}