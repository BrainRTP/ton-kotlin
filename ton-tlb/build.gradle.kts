kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.tonCell)
            }
        }
    }
}