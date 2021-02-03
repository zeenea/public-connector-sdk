plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    `maven-publish`
}


group = "zeenea"
val versionBuildSuffix = if (System.getenv("GITHUB_RUN_NUMBER") == null) "" else ("+" + System.getenv("GITHUB_RUN_NUMBER"))
version = file("version.txt").readText().trim() + versionBuildSuffix
description = "public-connector-sdk"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

apply(from = "gradle/colored-output.gradle.kts")

tasks.withType<JavaCompile> {
    with(options) {
        encoding = "UTF-8"
    }
}

tasks.test {
    useJUnitPlatform()
}


tasks.register<Jar>("javadocJar") {
    archiveClassifier.set("javadoc")
    from(tasks.javadoc)
}

repositories {
    jcenter()
}

dependencies {
    api(group = "org.pf4j", name = "pf4j", version = "3.0.1")
    testImplementation(platform("org.junit:junit-bom:5.7.0"))
    testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api")
    testImplementation(group = "org.slf4j", name = "slf4j-api", version = "1.7.30")
    testRuntimeOnly(group = "org.junit.jupiter", name = "junit-jupiter-engine")
}


publishing {
    publications {
        create<MavenPublication>("public-connector-sdk") {
            from(components["java"])
            artifact(tasks["javadocJar"])
        }
    }
    repositories {
        maven {
            name = "public-connector-sdk"
            url = uri("https://maven.pkg.github.com/zeenea/public-connector-sdk")
            credentials {
                username = project.findProperty("github.actor") as String? ?: System.getenv("GITHUB_ACTOR")
                password = project.findProperty("github.token") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
