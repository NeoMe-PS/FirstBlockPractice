pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "FirstBlockPractice"
include(":app")
include(":auth")
include(":splash")
include(":core")
include(":help")
include(":news")
include(":history")
include(":user")
include(":search")
