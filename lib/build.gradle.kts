
plugins {
    id("java-library")
    id("kotlin")
}
dependencies {

    implementation("com.google.code.gson:gson:2.8.5")
    implementation(kotlin("stdlib-jdk7", rootProject.ext["kotlin_version"].toString()))
}
