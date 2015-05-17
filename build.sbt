name := "WCFont"

version := "1.0"

lazy val `wcfont` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.beust" % "jcommander" % "1.48",
  "com.roundeights" %% "hasher" % "1.0.0"
)
