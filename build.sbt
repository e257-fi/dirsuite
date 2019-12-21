import Dependencies._

/**
 * Build settings for DirSuite
 */

lazy val scala212 = "2.12.10"
lazy val scala213 = "2.13.1"
lazy val supportedScalaVersions = List(scala212, scala213)

lazy val commonSettings = Seq(
  organization := "fi.e257.testing",
  scalaVersion := scala213,
  scalacOptions ++= Seq(
	"-Xlint",
	"-deprecation",
	"-feature",
	"-unchecked",
	"-Xfatal-warnings"),
  wartremoverErrors ++= Warts.allBut(
    Wart.Throw //https://github.com/puffnfresh/wartremover/commit/869763999fcc1fd685c1a8038c974854457b608f
  )
)

lazy val dirsuite = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    crossScalaVersions := supportedScalaVersions,
    name := "dirsuite",
    version := "0.32.0-SNAPSHOT",
    fork in run := true,
    libraryDependencies += betterFiles,
    libraryDependencies += scalatest
  )

