load("@rules_java//java:defs.bzl", "java_binary", "java_library", "java_import")

package(default_visibility = ["//visibility:public"])

java_library(
    name = "java-maven-lib",
    srcs = glob(["src/main/java/com/example/myproject/*.java"]),
    deps = [
        ":mvn_google_guava",
    ],
)

java_binary(
    name = "java-maven",
    main_class = "com.example.myproject.App",
    runtime_deps = [":java-maven-lib"],
)

java_import(
    name = "mvn_google_guava",
    jars = ["@maven_google_guava//:jar"],
    tags = ["maven_coordinates=com.google.guava:guava:31.1-jre"],
)