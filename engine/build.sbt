import com.lightbend.lagom.core.LagomVersion

name := "mapping-engine"
version := "0.1"
scalaVersion in ThisBuild := "2.13.5"

version in ThisBuild := "1.0-SNAPSHOT"
organization in ThisBuild := "com.myalc"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.3" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.1.1" % Test
val uPickle = "com.lihaoyi" %% "upickle" % "1.3.8"
val scalate = "org.scalatra.scalate" %% "scalate-core" % "1.9.6"
val logger = "com.typesafe.scala-logging" %% "scala-logging" % "3.9.3"

resolvers += "jitpack".at("https://jitpack.io")
val circleSchemaVal = "io.circe" %% "circe-json-schema" % "0.2.0"
val circleLiteral = "io.circe" %% "circe-literal" % "0.14.1"
val circeCore = "io.circe" %% "circe-core" % "0.14.1"
val circeParser = "io.circe" %% "circe-parser" % "0.14.1"

val commonsCodec = "commons-codec" % "commons-codec" % "1.15"
val actor = "com.typesafe.akka" %% "akka-stream-typed" % LagomVersion.akka

libraryDependencies ++= Seq(
  circleSchemaVal,
  circleLiteral,
  circeCore,
  circeParser,
  commonsCodec,
  actor
)

lagomCassandraPort in ThisBuild := 9042
lagomCassandraEnabled in ThisBuild := false
lagomKafkaEnabled in ThisBuild := true
lagomKafkaPort in ThisBuild := 9092


lazy val `mapping-engine` = (project in file("."))
.aggregate(`service`, `implementation`)

lazy val `service` = (project in file("service"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi,
      logger
    )
  )

lazy val `implementation` = (project in file("implementation"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest,
      circleSchemaVal,
      circleLiteral,
      circeCore,
      circeParser,
      uPickle,
      scalate,
      logger,
      commonsCodec,
      actor
    )
  )
  .settings(lagomForkedTestSettings)
  .dependsOn(`service`)
