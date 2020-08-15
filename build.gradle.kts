plugins {
    java
    id("fabric-loom") version "0.2.7-SNAPSHOT"
}

group = "com.proximyst"
version = "0.1.0"

minecraft {}

repositories {
    maven {
        name = "proxi-nexus"
        url = uri("https://nexus.proximyst.com/repository/maven-public/")
    }

    jcenter()
    mavenCentral()
}

configurations.modCompile.get().extendsFrom(configurations.include.get())

dependencies {
    minecraft("com.mojang:minecraft:${project.properties["com.mojang.minecraft.version"]}")
    mappings("net.fabricmc:yarn:${project.properties["net.fabricmc.yarn.version"]}:v2")

    modCompile("net.fabricmc:fabric-loader:${project.properties["net.fabricmc.loader.version"]}")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<JavaCompile> {
    sourceCompatibility = "1.8"
    targetCompatibility = sourceCompatibility
    options.encoding = "UTF-8"
}

tasks.withType<AbstractArchiveTask> {
    archiveBaseName.set("righthanded-players")
}

tasks.withType<ProcessResources> {
    inputs.property("version", project.version)

    from(sourceSets["main"].resources.srcDirs) {
        include("fabric.mod.json")
        expand("version" to project.version)
    }

    from(sourceSets["main"].resources.srcDirs) {
        exclude("fabric.mod.json")
    }
}
