tasks.withType<Test> {
    configure<JacocoTaskExtension> {
        isIncludeNoLocationClasses = true
    }
}
tasks.withType<JacocoReport> {
    setDependsOn(listOf("testDebugUnitTest", "createDebugCoverageReport"))
    reports.apply {
        xml.isEnabled = true
        html.isEnabled = true
        html.destination = file("${project.buildDir}/jacocoHtml")
    }
    val fileFilter = listOf("**/R.class",
                            "**/R$*.class",
                            "**/BuildConfig.*",
                            "**/Manifest*.*",
                            "**/*Test*.*",
                            "android/**/*.*")
    val debugTree = fileTree(mapOf("dir" to "$project.buildDir/tmp/kotlin-classes/debug",
                                   "excludes" to fileFilter))
    val mainSrc = "$project.projectDir/src/main/kotlin"

    setSourceDirectories(files(listOf(mainSrc)))
    setClassDirectories(files(listOf(debugTree)))
    setExecutionData(fileTree(mapOf("dir" to project.buildDir,
                               "includes" to listOf("jacoco/testDebugUnitTest.exec",
                                                    "outputs/code-coverage/connected/*coverage.ec"))))
}
