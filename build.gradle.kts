plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    `maven-publish`
    id("com.diffplug.spotless") version "6.25.0"
    id("com.github.spotbugs") version "6.0.14"
}


group = "zeenea"
version = file("version.txt").readText().trim()
description = "public-connector-sdk"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

spotless {
    java {
        googleJavaFormat()
    }
}

spotbugs {
    val excludeFile = file("${rootDir}/spotbug-exclude.xml")
    if (excludeFile.exists()) {
        excludeFilter.set(excludeFile)
    }
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

tasks.withType<Javadoc> {
    options {

        memberLevel = JavadocMemberLevel.PUBLIC

        this as StandardJavadocDocletOptions
        noQualifiers = listOf("java.lang", "java.util", "java.time")
    }
}

tasks {
    val sourcesJar by creating(Jar::class) {
        archiveClassifier.set("sources")
        from(sourceSets.main.get().allSource)
    }

    val javadocJar by creating(Jar::class) {
        dependsOn.add(javadoc)
        archiveClassifier.set("javadoc")
        from(javadoc)
    }

    artifacts {
        archives(sourcesJar)
        archives(javadocJar)
        archives(jar)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    val pf4jVersion: String by project
    api(group = "org.pf4j", name = "pf4j", version = pf4jVersion)

    val slf4jVersion: String by project
    testImplementation(group = "org.slf4j", name = "slf4j-api", version = slf4jVersion)

    val junitVersion: String by project
    testImplementation(platform("org.junit:junit-bom:${junitVersion}"))
    testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api")
    testRuntimeOnly(group = "org.junit.jupiter", name = "junit-jupiter-engine")

    val jetbrainsAnnotationsVersion: String by project
    compileOnly(
        group = "org.jetbrains",
        name = "annotations",
        version = jetbrainsAnnotationsVersion
    )
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
