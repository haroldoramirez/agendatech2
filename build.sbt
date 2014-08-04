import play.PlayJava

name := """agendatech2"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "mysql" % "mysql-connector-java" % "5.1.31",
  "postgresql" % "postgresql" % "9.1-901.jdbc4",
  "commons-io" % "commons-io" % "2.4",
  "javax.mail" % "mail" % "1.4.5",
  "com.typesafe.play.plugins" %% "play-plugins-mailer" % "2.3.0",
  "com.typesafe.play.plugins" %% "play-plugins-util" % "2.3.0",
  "org.glassfish.web" % "javax.el" % "2.2.6"
)