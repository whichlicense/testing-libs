/*
 * Copyright (c) 2023 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/whichlicense/testing-libs.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
plugins {
    id("java-library")
    id("org.graalvm.buildtools.native") version "0.9.20"
    id("maven-publish")
    id("signing")
}

group = "com.whichlicense.testing"
version = "0.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(19))
    }
    withJavadocJar()
    withSourcesJar()
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/whichlicense/testing-libs")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
        }
    }
}

dependencies {
    api("org.junit.jupiter:junit-jupiter-api:5.9.2")
    api("org.junit.jupiter:junit-jupiter-params:5.9.2")
    testImplementation("org.mockito:mockito-subclass:5.2.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.1.1")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("com.whichlicense.testing:naming:0.0.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("--enable-preview")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
    jvmArgs("--enable-preview")
}

graalvmNative {
    metadataRepository {
        enabled.set(true)
    }
}

publishing {
    publications {
        create<MavenPublication>("enumpowerset") {
            artifactId = "enumpowerset"
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set("WhichLicense testing-libs/enumpowerset")
                description.set("This library provides an internal JUnit 5 extension for enums.")
                url.set("https://github.com/whichlicense/testing-libs/enumpowerset")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("grevend")
                        name.set("David Greven")
                        email.set("david.greven@whichlicense.com")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/whichlicense/testing-libs.git")
                    developerConnection.set("scm:git:git@github.com:whichlicense/testing-libs.git")
                    url.set("https://github.com/whichlicense/testing-libs")
                }
            }
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/whichlicense/testing-libs")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

signing {
    if (project.hasProperty("CI")) {
        val signingKey = System.getenv("PKG_SIGNING_KEY")
        val signingPassword = System.getenv("PKG_SIGNING_PW")
        useInMemoryPgpKeys(signingKey, signingPassword)
        sign(publishing.publications["enumpowerset"])
    }
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}
