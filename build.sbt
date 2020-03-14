import org.scoverage.coveralls.Imports.CoverallsKeys._

name := "finagle-prometheus"

lazy val commonSettings = Seq(
  organization := "com.samstarling",
  scalaVersion := "2.12.6",
  licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
)

// crossScalaVersions := Seq("2.11.11", "2.12.6")

organization := "com.samstarling"

val finagleVersion = "19.12.0"

libraryDependencies ++= Seq(
  "com.twitter" %% "finagle-core" % finagleVersion,
  "com.twitter" %% "finagle-http" % finagleVersion,
  "com.twitter" %% "finagle-stats" % finagleVersion,
  "io.prometheus" % "simpleclient" % "0.5.0",
  "io.prometheus" % "simpleclient_common" % "0.5.0",
  "org.specs2" %% "specs2-core" % "4.3.3" % "test",
  "org.specs2" %% "specs2-mock" % "4.3.3" % "test"
)

parallelExecution in Test := true
parallelExecution in ThisBuild := false

lazy val core = (project in file("."))
  .settings(commonSettings: _*)
  .settings(coverageEnabled := false,
            coverallsTokenFile := Some("src/test/resources/coveralls-key.txt"),
            releaseCrossBuild := true)

lazy val examples = (project in file("examples"))
  .settings(commonSettings: _*)
  .dependsOn(core)
  .settings()

lazy val root = project.aggregate(core, examples)

homepage := Some(url("https://github.com/samstarling/finagle-prometheus"))

pomExtra :=
  <scm>
    <connection>
      scm:git:git://github.com/samstarling/finagle-prometheus.git
    </connection>
    <url>
      https://github.com/samstarling/finagle-prometheus
    </url>
  </scm>
  <developers>
    <developer>
      <id>samstarling</id>
      <name>Sam Starling</name>
      <email>mail@samstarling.co.uk</email>
    </developer>
  </developers>
