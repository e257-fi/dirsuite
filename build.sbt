import Dependencies._

/**
 * Build settings for DirSuite
 */

lazy val commonSettings = Seq(
  organization := "fi.e257.testing",
  scalaVersion := "2.12.3",
  scalacOptions ++= Seq(
	"-Xlint",
	"-deprecation",
	"-feature",
	"-unchecked",
	"-Xfatal-warnings"),
  wartremoverErrors ++= Warts.allBut(
    Wart.Throw //https://github.com/puffnfresh/wartremover/commit/869763999fcc1fd685c1a8038c974854457b608f
  ),
  publishTo := sonatypePublishTo.value,
)

lazy val dirsuite = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "dirsuite",
    version := "0.20.0",
    fork in run := true,
    libraryDependencies += betterFiles,
    libraryDependencies += scalaArm,
    libraryDependencies += scalatest
  )

