package dk.michaelbui.metis.buildsrc

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.provider.Property
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.*
import javax.inject.Inject

abstract class MetisPluginGradlePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        val extension =
            target.extensions.create("metisPlugin", MetisPluginGradlePluginExtension::class.java, target.objects)
        applyPlugins(target)
        applyDependencies(target, extension)
        applyProjectConfiguration(target, extension)
    }

    private fun applyPlugins(project: Project) {
        project.apply {
            plugin("java")
        }
    }

    private fun applyDependencies(project: Project, extension: MetisPluginGradlePluginExtension) {
        val implementation = "implementation"
        val annotationProcessor = "annotationProcessor"

        project.dependencies.apply {
            add(implementation, project(":metis-core"))
            add(implementation, project(":metis-plugin"))
            add(implementation, extension.pf4jDependency.orElse(""))
            add(annotationProcessor, extension.pf4jDependency.orElse(""))
        }
    }

    private fun applyProjectConfiguration(project: Project, extension: MetisPluginGradlePluginExtension) {
        val javaExtension = project.extensions.getByType(JavaPluginExtension::class.java)
        javaExtension.apply {
            sourceCompatibility = JavaVersion.VERSION_21
            targetCompatibility = JavaVersion.VERSION_21
        }

        project.plugins.withId("java") {
            project.tasks.withType(Jar::class.java).configureEach {
                manifest {
                    attributes(
                        "Plugin-Id" to extension.pluginId.orElse(""),
                        "Plugin-Version" to extension.pluginVersion.orElse(""),
                        "Plugin-Provider" to extension.pluginProvider.getOrElse("N/A")
                    )
                }
            }
        }

        project.repositories {
            mavenCentral()
        }
    }
}

abstract class MetisPluginGradlePluginExtension @Inject constructor(objects: ObjectFactory) {
    val pluginId: Property<String> = objects.property(String::class.java)
    val pluginVersion: Property<String> = objects.property(String::class.java)
    val pluginProvider: Property<String?> = objects.property(String::class.java)
    val pf4jDependency: Property<String> = objects.property(String::class.java)
}