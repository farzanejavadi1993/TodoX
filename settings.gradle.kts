pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "TodoX"
include(":app")
include(":core:designsystem")
include(":core:ui")
include(":core:data")
include(":core:domain")
include(":core:model")
include(":feature:auth")
include(":feature:tasklist")
include(":feature:task")
include(":feature:project")
include(":feature:search")
include(":feature:setting")


