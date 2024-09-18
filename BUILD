load("@aspect_bazel_lib//lib:tar.bzl", "tar")
load("@rules_java//java:defs.bzl", "java_binary", "java_library", "java_test")

package(default_visibility = ["//visibility:public"])

java_library(
    name = "java-maven-lib",
    srcs = glob(["src/main/java/com/example/myproject/*.java"]),
    #    deps = ["@maven//:com_google_guava_guava"],
    deps = [
        "@mvn_google_guava//jar",
        "@mvn_google_guava_failureaccess//jar",
    ],
)

java_binary(
    name = "java-maven",
    main_class = "com.example.myproject.App",
    runtime_deps = [":java-maven-lib"],
)
