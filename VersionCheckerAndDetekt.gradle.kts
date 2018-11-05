plugins {
    id("io.gitlab.arturbosch.detekt").version("1.0.0.RC9.2")
    id("com.github.ben-manes.versions").version("1.0.0.RC9.2")
}

subprojects {
    apply {
        plugin("io.gitlab.arturbosch.detekt")
    }

    detekt {
        toolVersion = "1.0.0.RC9.2"
        debug = true
        parallel = true
        input = files("src/main/java")
        filters = ".*/resources/.*,.*/build/.*"
        config = files("$rootDir/detekt.yml")

        idea {
            path = "$rootDir/.idea"
            codeStyleScheme = "$rootDir/.idea/idea-code-style.xml"
            inspectionsProfile = "$rootDir/.idea/inspect.xml"
            mask = "*.kt"
        }
    }
}
