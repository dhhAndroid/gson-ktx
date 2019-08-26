
plugins {
    id("java-library")
    id("kotlin")
}
dependencies {
    compileOnly("com.google.code.gson:gson:2.8.5")
    compileOnly(kotlin("stdlib-jdk7", rootProject.ext["kotlin_version"].toString()))
}
