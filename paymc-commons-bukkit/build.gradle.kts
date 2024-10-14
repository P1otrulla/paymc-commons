import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    id("io.github.goooler.shadow") version "8.1.7"
}

group = "dev.piotrulla.paymc"
version = "1.0.0"
val mainPackage = "$group.commons"

repositories {
    mavenCentral()

    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://storehouse.okaeri.eu/repository/maven-public/")
    maven("https://repo.stellardrift.ca/repository/snapshots/")
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://repo.eternalcode.pl/snapshots")
    maven("https://repo.eternalcode.pl/releases")
    maven("https://repo.piotrulla.dev/releases")
    maven("https://jitpack.io")
}

dependencies {
    implementation(project(":paymc-commons-shared"))

    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")

    val okaeriConfigsVersion = "5.0.5"
    implementation("eu.okaeri:okaeri-configs-serdes-commons:${okaeriConfigsVersion}")
    implementation("eu.okaeri:okaeri-configs-serdes-bukkit:${okaeriConfigsVersion}")

    implementation("com.eternalcode:eternalcode-commons-adventure:1.1.4-SNAPSHOT")

    // vault-api
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks.withType<JavaCompile> {
    options.compilerArgs = listOf("-Xlint:deprecation")
    options.encoding = "UTF-8"
}

tasks.withType<ShadowJar> {
    archiveFileName.set("paymc-commons-shared -${project.version}.jar")

    exclude(
        "org/intellij/lang/annotations/**",
        "org/jetbrains/annotations/**",
        "org/checkerframework/**",
        "META-INF/**",
        "javax/**",
    )

}