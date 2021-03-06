plugins {
    application
    kotlin("jvm") version "1.6.10"
}

group = "com.sample"
version = "0.0.1"
application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:${Versions.ktor}")
    implementation("io.ktor:ktor-server-host-common:${Versions.ktor}")
    implementation("io.ktor:ktor-gson:${Versions.ktor}")
    implementation("io.ktor:ktor-server-netty:${Versions.ktor}")
    implementation("io.ktor:ktor-auth:${Versions.ktor}")
    implementation("io.ktor:ktor-auth-jwt:${Versions.ktor}")
    implementation("ch.qos.logback:logback-classic:${Versions.logback}")
    implementation("io.ktor:ktor-locations:${Versions.ktor}")

    implementation("io.insert-koin:koin-ktor:${Versions.koin}")
    implementation("io.insert-koin:koin-logger-slf4j:${Versions.koin}")

    implementation("org.litote.kmongo:kmongo-coroutine:${Versions.kmongo}")
    implementation("org.valiktor:valiktor-core:${Versions.valiktor}")
    implementation("commons-codec:commons-codec:${Versions.commons}")
    implementation("ch.qos.logback:logback-classic:${Versions.logback}")
}