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
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    val pf4jVersion: String by project
    api(group = "org.pf4j", name = "pf4j", version = pf4jVersion)

    // Common properties are used to extract the associated UUIDs
    implementation(group = "com.github.zeenea", name = "common-properties", version = "4.4")

    val slf4jVersion: String by project
    testImplementation(group = "org.slf4j", name = "slf4j-api", version = slf4jVersion)

    val junitVersion: String by project
    testImplementation(platform("org.junit:junit-bom:${junitVersion}"))
    testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api")
    testRuntimeOnly(group = "org.junit.jupiter", name = "junit-jupiter-engine")
    val assertjVersion: String by project
    testImplementation(group = "org.assertj", name = "assertj-core", version = assertjVersion)

    val jetbrainsAnnotationsVersion: String by project
    compileOnly(
        group = "org.jetbrains",
        name = "annotations",
        version = jetbrainsAnnotationsVersion
    )
}

@Suppress("UNCHECKED_CAST")
fun groovy.util.Node.childText(name: String): String? {
    val list = this.get(name) as? MutableList<*>
    val node = list?.firstOrNull() as? groovy.util.Node
    return node?.text()
}

@Suppress("UNCHECKED_CAST")
fun groovy.util.Node.replaceNode(name: String, value: String) {
    val list = this.get(name) as? MutableList<*>
    val node = list?.firstOrNull() as? groovy.util.Node
    node?.setValue(value)
}

publishing {
    publications {
        create<MavenPublication>("public-connector-sdk") {
            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])
            pom.withXml {
                val root = asNode()

                val dependenciesNode: groovy.util.Node =
                    (root.get("dependencies") as? MutableList<*>)?.firstOrNull()
                            as? groovy.util.Node
                        ?: root.appendNode("dependencies")

                // --- Rename the Jitpack dependencies ---
                dependenciesNode.children().filterIsInstance<groovy.util.Node>()
                    .filter { dep ->
                        dep.childText("groupId") == "com.github.zeenea"
                    }
                    .forEach { unwanted ->
                        unwanted.replaceNode("groupId", "zeenea")
                    }


            }
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
