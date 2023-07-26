plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.15.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.15.0")
    implementation("com.fasterxml.uuid:java-uuid-generator:3.1.5")
    compileOnly("org.projectlombok:lombok:1.18.24")





}

tasks.test {
    useJUnitPlatform()
}