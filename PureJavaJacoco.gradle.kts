tasks.withType<JacocoReport> {
    reports.apply {
        xml.isEnabled = true
        csv.isEnabled = true
        html.isEnabled = true
        html.destination = file("${project.buildDir}/jacocoHtml")
    }
}
