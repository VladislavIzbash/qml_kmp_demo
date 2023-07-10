plugins {
    kotlin("multiplatform").version("1.9.0")
}

kotlin {
    js(IR) {
        useEsModules()

        browser()

        binaries.library()
    }
}