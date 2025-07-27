import dk.michaelbui.metis.buildsrc.MetisPluginGradlePlugin

plugins {
    id("java")
    id("dk.michaelbui.metis.buildsrc.metisplugin")
}

group = "dk.michaelbui.metis"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.jackson.databind)
    implementation(libs.jackson.datatype.jsr310)

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

metisPlugin {
    pf4jDependency = libs.pf4j.get().toString()
    pluginId = "metis-eventsink-builtin-discord"
    pluginVersion = "0.1.0"
    pluginProvider = "Michael Bui"
}