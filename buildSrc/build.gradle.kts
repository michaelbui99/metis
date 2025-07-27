plugins {
    `kotlin-dsl`
}

repositories{
    mavenCentral()
}

gradlePlugin{
    plugins{
        create("metis-plugin"){
            id = "dk.michaelbui.metis.buildsrc.metisplugin"
            implementationClass = "dk.michaelbui.metis.buildsrc.MetisPluginGradlePlugin"
        }
    }
}