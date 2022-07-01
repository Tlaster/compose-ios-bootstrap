
import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

plugins {
    kotlin("multiplatform") version "1.6.21"
    id("org.jetbrains.compose") version ".2.0-alpha01-dev731"
}

version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

kotlin {
    iosX64("uikitX64") {
        binaries {
            executable() {
                entryPoint = "main"
                freeCompilerArgs += listOf(
                    "-linker-option", "-framework", "-linker-option", "Metal",
                    "-linker-option", "-framework", "-linker-option", "CoreText",
                    "-linker-option", "-framework", "-linker-option", "CoreGraphics"
                )
            }
        }
    }
    iosArm64("uikitArm64") {
        binaries {
            executable() {
                entryPoint = "main"
                freeCompilerArgs += listOf(
                    "-linker-option", "-framework", "-linker-option", "Metal",
                    "-linker-option", "-framework", "-linker-option", "CoreText",
                    "-linker-option", "-framework", "-linker-option", "CoreGraphics"
                )
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.ui)
                implementation(compose.foundation)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.material3)
                implementation(compose.runtime)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        
        val uikitMain by creating {
            dependsOn(commonMain)
        }
        val uikitX64Main by getting {
            dependsOn(uikitMain)
        }
        val uikitArm64Main by getting {
            dependsOn(uikitMain)
        }
    }
}

compose.experimental {
    uikit.application {
        bundleIdPrefix = "moe.tlaster"
        projectName = "ComposeBootstrap"
        deployConfigurations {
            simulator("Simulator") {
                device = org.jetbrains.compose.experimental.dsl.IOSDevices.IPHONE_13_MINI
            }
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

kotlin {
    targets.withType<KotlinNativeTarget> {
        binaries.all {
        }
    }
}