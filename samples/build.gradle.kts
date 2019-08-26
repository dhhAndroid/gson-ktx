plugins {
    id("java-library")
    id("kotlin")
}
dependencies {
    implementation(kotlin("stdlib-jdk7"))
    implementation(project(":lib"))
}
