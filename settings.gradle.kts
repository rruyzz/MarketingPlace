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

rootProject.name = "MarketingPlace"
include(":app")
include(":core:network")
include(":core:common")
include(":feature:search:data")
include(":feature:search:domain")
include(":feature:search:presentation")
include(":feature:productlist:data")
include(":feature:productlist:domain")
include(":feature:productlist:presentation")
include(":core:navigation")
include(":feature:productdetail:data")
include(":feature:productdetail:domain")
include(":feature:productdetail:presentation")
