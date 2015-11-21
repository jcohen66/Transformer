name := "Transformer"

version := "1.0"

scalaVersion := "2.11.6"

organization := "com.example"

resolvers ++= Seq("Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Spray Repository" at "http://repo.spray.io")



libraryDependencies ++= {
  val akkaVersion = "2.3.9"
  val sprayVersion = "1.3.3"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "io.spray" %% "spray-can" % sprayVersion,
    "io.spray" %% "spray-routing" % sprayVersion,
    "io.spray" %% "spray-json" % "1.3.1",
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "ch.qos.logback" % "logback-classic" % "1.1.3",
    "ch.qos.logback" % "logback-core" % "1.1.3",
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
    "org.scalatest" %% "scalatest" % "2.2.0" % "test",
    "org.mockito" % "mockito-core" % "1.9.5",
    "com.fasterxml.jackson.core" % "jackson-core" % "2.6.3",
    "com.fasterxml.jackson.core" % "jackson-databind" % "2.6.3",
    "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.6.3",
    "junit" % "junit" % "4.12",
    "com.lambdaworks" %% "jacks" % "2.3.3"

  )
}