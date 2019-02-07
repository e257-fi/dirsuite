import sbt._

object Dependencies {

  /*
   * Versions
   */
  val betterFilesVersion = "3.7.0"
  val scalatestVersion = "3.0.5"

  /*
   * Libraries: scala
   */
  val betterFiles = "com.github.pathikrit" %% "better-files" % betterFilesVersion
  val scalatest = "org.scalatest" %% "scalatest" % scalatestVersion

}
