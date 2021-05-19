pluginManagement {
    repositories {
        jcenter()
        maven(url = "https://maven.fabricmc.net/") {
            name = "Fabric"
        }
        gradlePluginPortal()
    }

    val loom_version: String by settings
    val kotlin_version: String by settings

    plugins {
        kotlin("jvm") version kotlin_version
        id("fabric-loom") version loom_version
    }

}
