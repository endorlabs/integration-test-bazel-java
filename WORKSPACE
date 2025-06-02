load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive", "http_jar")

http_archive(
    name = "rules_java",
    urls = [
        "https://github.com/bazelbuild/rules_java/releases/download/8.12.0/rules_java-8.12.0.tar.gz",
    ],
    sha256 = "1558508fc6c348d7f99477bd21681e5746936f15f0436b5f4233e30832a590f9",
)

load("@rules_java//java:rules_java_deps.bzl", "rules_java_dependencies")
rules_java_dependencies()

load("@bazel_features//:deps.bzl", "bazel_features_deps")
bazel_features_deps()

# note that the following line is what is minimally required from protobuf for the java rules
# consider using the protobuf_deps() public API from @com_google_protobuf//:protobuf_deps.bzl
load("@com_google_protobuf//bazel/private:proto_bazel_features.bzl", "proto_bazel_features")  # buildifier: disable=bzl-visibility
proto_bazel_features(name = "proto_bazel_features")

http_jar(
    name = "mvn_google_guava_failureaccess",
    url = "https://repo1.maven.org/maven2/com/google/guava/failureaccess/1.0.1/failureaccess-1.0.1.jar",
    sha256 = "a171ee4c734dd2da837e4b16be9df4661afab72a41adaf31eb84dfdaf936ca26",
)

http_jar(
    name = "mvn_google_guava_listenablefuture",
    url = "https://repo1.maven.org/maven2/com/google/guava/listenablefuture/9999.0-empty-to-avoid-conflict-with-guava/listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar",
    sha256 = "b372a037d4230aa57fbeffdef30fd6123f9c0c2db85d0aced00c91b974f33f99",
)

http_jar(
    name = "mvn_com_google_code_findbugs_jsr305",
    url = "https://repo1.maven.org/maven2/com/google/code/findbugs/jsr305/3.0.2/jsr305-3.0.2.jar",
    sha256 = "766ad2a0783f2687962c8ad74ceecc38a28b9f72a2d085ee438b7813e928d0c7",
)

http_jar(
    name = "mvn_org_checkerframework_checker_qual",
    url = "https://repo1.maven.org/maven2/org/checkerframework/checker-qual/3.49.3/checker-qual-3.49.3.jar",
    sha256 = "367edbf2fe9f606c1fdb5a8ba6e1c9c27625993e1ff954e3868de70bcc6416b7",
)

http_jar(
    name = "mvn_com_google_errorprone_error_prone_annotations",
    url = "https://repo1.maven.org/maven2/com/google/errorprone/error_prone_annotations/2.24.1/error_prone_annotations-2.24.1.jar",
    sha256 = "19fe2f7155d20ea093168527999da98108103ee546d1e8b726bc4b27c31a3c30",
)

http_jar(
    name = "mvn_com_google_j2objc_j2objc_annotations",
    url = "https://repo1.maven.org/maven2/com/google/j2objc/j2objc-annotations/2.8/j2objc-annotations-2.8.jar",
    sha256 = "f02a95fa1a5e95edb3ed859fd0fb7df709d121a35290eff8b74dce2ab7f4d6ed",
)



## Our Wonderful (MuchHackz) Test Case Mimicking what Rubrik does.
## They get the source jar where possible, Recompile it themselves.
http_archive(
    name = "maven_google_guava",
    url = "https://repo1.maven.org/maven2/com/google/guava/guava/31.1-jre/guava-31.1-jre-sources.jar",
    sha256 = "8ab1853cdaf936ec88be80c17302b7c20abafbd4f54d4fb54d7011c529e3a44a",
    build_file_content = """
load("@rules_java//java:defs.bzl", "java_library")

package(default_visibility = ["//visibility:public"])

java_binary(
    name = "bin",
    srcs = glob(["com/**/*.java"]),
    resources = glob(["META-INF/**"]),
    visibility = ["//visibility:public"],
    main_class = "com.google.guava.App", ## Non Existent Main Class.
    javacopts = ["-source", "8", "-target", "8", "-Xep:NoCanIgnoreReturnValueOnClasses:OFF"],
    deps = [
        "@mvn_google_guava_failureaccess//jar",
        "@mvn_google_guava_listenablefuture//jar",
        "@mvn_com_google_code_findbugs_jsr305//jar",
        "@mvn_org_checkerframework_checker_qual//jar",
        "@mvn_com_google_j2objc_j2objc_annotations//jar",
        "@mvn_com_google_errorprone_error_prone_annotations//jar",
    ],
)

## This Generates a Fat Jar with all the dependencies.
## Customers usually create Simple Jars for Each Dependency and Import them.
## This is to get it working for the Test Case.
genrule(
    name = "jar",
    srcs = [":bin_deploy.jar"],
    outs = ["uber.jar"],
    cmd = "cp $(location :bin_deploy.jar) $@",
)
"""
)
