import com.android.build.gradle.internal.tasks.JacocoTask
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension

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

    sourceDirectories = files(listOf(mainSrc))
    classDirectories = files(listOf(debugTree))
    executionData = fileTree(mapOf("dir" to project.buildDir,
                                   "includes" to listOf("jacoco/testDebugUnitTest.exec",
                                                        "outputs/code-coverage/connected/*coverage.ec")))
}
