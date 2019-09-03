import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    id("java-library")
    id("kotlin")
    id("com.novoda.bintray-release")
}
dependencies {
    compileOnly("com.google.code.gson:gson:2.8.5")
    compileOnly(kotlin("stdlib-jdk7", rootProject.ext["kotlin_version"].toString()))
}

tasks.whenTaskAdded {
    if (name == "generateSourcesJarForMavenPublication") {
        this as Jar
        from(sourceSets.main.get().allSource)
    }
}
val loadProperties = loadProperties(rootProject.file("local.properties").absolutePath)
publish {
    userOrg = "dhhandroid"
    groupId = "com.dhh"
    artifactId = "gson-ktx"
    publishVersion = "1.0.2"
    desc = "A set of Kotlin extensions for Gson"
    website = "https://github.com/dhhAndroid/gson-ktx"
    bintrayUser = loadProperties.getProperty("bintray.user")
    bintrayKey = loadProperties.getProperty("bintray.apikey")
    dryRun = false
}
