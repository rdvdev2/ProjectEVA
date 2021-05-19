plugins {
    kotlin("jvm")
    id("fabric-loom")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

project.version = findProperty("mod_version")!!
project.group = findProperty("maven_group")!!

repositories {

}

dependencies {
    minecraft("com.mojang:minecraft:${findProperty("minecraft_version")}")
    mappings("net.fabricmc:yarn:${findProperty("yarn_mappings")}:v2")
    modImplementation("net.fabricmc:fabric-loader:${findProperty("loader_version")}")
    modImplementation("net.fabricmc:fabric-language-kotlin:${findProperty("fabric_kotlin_version")}")

    // Fabric API
    setOf(
        "fabric-api-base"
    ).forEach {
        modImplementation(fabricApi.module(it, findProperty("fabric_version") as String?))
    }
}

tasks.getByName<ProcessResources>("processResources"){
    inputs.property("version", project.version)

    filesMatching("fabric.mod.json") {
        expand(
            mutableMapOf(
                "version" to project.version
            )
        )
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

tasks.withType<JavaCompile>().configureEach {
    this.options.encoding = "UTF-8"

    val targetVersion = 8
    if (JavaVersion.current().isJava9Compatible) {
        this.options.release.set(targetVersion)
    }
}

tasks.getByName<Jar>("jar") {
    from ("LICENSE") {
        rename { "${it}_${findProperty("archivesBaseName")}"}
    }
}