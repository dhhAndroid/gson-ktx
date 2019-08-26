buildscript {
    val kotlin_version = "1.3.50"
    extra["kotlin_version"] = kotlin_version
    repositories {
        jcenter()
        google()
    }
    dependencies {
        classpath(kotlin("gradle-plugin", kotlin_version))
    }
}
allprojects {
    repositories {
        jcenter()
        google()
    }
}

task("clean", Delete::class) {
    delete(rootProject.buildDir)
}