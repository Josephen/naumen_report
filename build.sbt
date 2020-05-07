import Dependencies._

ThisBuild / scalaVersion     := "2.13.1"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "chimney pres",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "io.scalaland" %% "chimney" % "0.5.1",
      libraryDependencies += "io.scalaland" %% "chimney-cats" % "0.5.1",
    libraryDependencies += "org.typelevel" %% "cats-core" % "2.0.0"

)

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
