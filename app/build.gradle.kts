plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    idea
    war
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // This dependency is used by the application.
    implementation(libs.bundles.lucene)
    implementation(libs.bundles.spring)

    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    testCompileOnly(libs.lombok)
    testAnnotationProcessor(libs.lombok)

    testImplementation(libs.bundles.mockito)
    implementation(libs.bundles.hibernate)
    implementation(libs.bundles.jakarta)

    implementation(libs.bundles.log4j)
    implementation(libs.bundles.jackson)

    implementation(libs.jstl)
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use JUnit Jupiter test framework
            useJUnitJupiter("5.11.1")
        }
    }
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}