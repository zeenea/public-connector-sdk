plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    `maven-publish`
    id("com.diffplug.spotless") version "6.25.0"
    id("com.github.spotbugs") version "6.4.4"
}


group = "zeenea"
version = System.getenv("VERSION") ?: "dev"
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
    mavenLocal()
    maven {
        name = "Zeenea Connector SDK"
        url = uri("https://maven.pkg.github.com/zeenea/common-properties")
        credentials {
            username =
                System.getenv("GITHUB_ACTOR") ?: project.findProperty("github.actor") as String?
            password =
                System.getenv("GITHUB_TOKEN") ?: project.findProperty("github.token") as String?
        }
    }
}
dependencies {
    api(libs.pf4j)
    // Common properties are used to extract the associated UUIDs
    implementation(libs.common.properties)
    testImplementation(libs.jackson.annotations)
    testImplementation(libs.jackson.core)
    testImplementation(libs.jackson.databind)
    api(libs.jts.core)
    testImplementation(libs.slf4j.api)
    testImplementation(platform(libs.junit.bom))
    testRuntimeOnly(libs.junit.platform.launcher)
    testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api")
    testRuntimeOnly(group = "org.junit.jupiter", name = "junit-jupiter-engine")
    testImplementation(libs.assertj.core)
    testImplementation(libs.equalsverifier)
    compileOnly(libs.annotations)
}

publishing {
    publications {
        create<MavenPublication>("public-connector-sdk") {
            from(components["java"])
            artifact(tasks["sourcesJar"])
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
