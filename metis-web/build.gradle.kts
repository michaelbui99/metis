import com.github.gradle.node.npm.task.NpmTask

plugins {
    id("com.github.node-gradle.node") version "7.1.0"
}

tasks.register("npmClean", type = NpmTask::class) {
    args.set(listOf("run", "clean"))
}

tasks.register("npmBuild", type = NpmTask::class) {
    dependsOn("npmInstall")
    args.set(listOf("run", "build"))
}
