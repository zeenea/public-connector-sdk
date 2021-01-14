plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    `maven-publish`
}


group = "zeenea"
version = file("version.txt").readText().trim()
description = "public-connector-sdk"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

apply(from = "buildtools/ColoredOutput.gradle")
project.extra["GITHUB_ACTIONS_FOLDING"] = true

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
    testImplementation(platform("org.junit:junit-bom:5.7.0"))
    testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api")
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
