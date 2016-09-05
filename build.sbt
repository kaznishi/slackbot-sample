name := """slack-scala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "mysql" % "mysql-connector-java" % "5.1.36",
  "com.typesafe.play" %% "play-slick" % "2.0.0",
//  "com.typesafe.play" %% "play-slick-evolutions" % "2.0.0",
  "com.bionicspirit" %% "shade" % "1.7.4",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "com.github.gilbertw1" %% "slack-scala-client" % "0.1.7",
  "org.json4s" %% "json4s-native" % "3.2.11",
  "org.json4s" %% "json4s-ext" % "3.2.11",
  "com.github.tototoshi" %% "play-json4s-native" % "0.5.0",
  "com.github.tototoshi" %% "play-json4s-test-native" % "0.5.0" % "test"
)

