# CameraX Sample

A quick sample project to show how to setup CameraX (1.0.0-beta03) using fragments. Related blogpost can be found here https://dev.to/funkyidol/exploring-camerax-beta-release-part-1-19hh

## Important Tips:
 * Do include the following in the build.gradle else CameraX throws compile time issues
 ```groovy
    android{
        compileOptions {
                sourceCompatibility JavaVersion.VERSION_1_8
                targetCompatibility JavaVersion.VERSION_1_8
        }
    }
 ```
