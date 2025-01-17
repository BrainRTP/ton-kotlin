kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.tonKotlinApi)
                api(libs.ktor.client.core)
                api(libs.ktor.client.cio)
                api(libs.ktor.server.cio)
                api(libs.ktor.network)
                implementation(libs.serialization.json)
                implementation(projects.tonKotlinLogger)
                implementation(libs.atomicfu)
                implementation(libs.datetime)
                implementation(libs.coroutines.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.coroutines.core)
                implementation(libs.coroutines.test)
                implementation(libs.coroutines.debug)
                implementation(libs.ktor.client.cio)
                implementation(libs.ktor.utils)
                implementation(projects.tonKotlinCrypto)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(libs.coroutines.jvm)
                implementation(libs.ktor.client.cio)
                implementation(libs.ktor.utils)
            }
        }
    }
}
