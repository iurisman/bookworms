lazy val akkaHttpVersion = "10.4.0"
lazy val akkaVersion = "2.7.0"
lazy val circeVersion = "0.14.3"

// Run in a separate JVM, to make sure sbt waits until all threads have
// finished before returning.
// If you want to keep the application running while executing other
// sbt tasks, consider https://github.com/spray/sbt-revolver/
fork := true

lazy val root = (project in file("."))
  .settings(
    inThisBuild(List(
      organization    := "urisman.net",
      scalaVersion    := "2.13.4"
    )),
    name := "bookworms",
    libraryDependencies ++= Seq(
      "com.typesafe.akka"  %% "akka-http"                % akkaHttpVersion,
      "com.typesafe.akka"  %% "akka-actor"               % akkaVersion,
      "com.typesafe.akka"  %% "akka-stream"              % akkaVersion,
      "ch.qos.logback"     % "logback-classic"           % "1.2.11",
      "ch.megard"          %% "akka-http-cors"           % "1.2.0",

      "com.typesafe.scala-logging" %% "scala-logging"    % "3.9.4",
      "com.typesafe.slick" %% "slick"                    % "3.4.1",
      "com.typesafe.slick" %% "slick-hikaricp"           % "3.4.1",
      "org.postgresql"     % "postgresql"                % "42.6.0",

      // JSON parsing
      "io.circe"           %% "circe-core"               % circeVersion,
      "io.circe"           %% "circe-generic"            % circeVersion,
      "io.circe"           %% "circe-parser"             % circeVersion,

      // Testing libs
      "com.typesafe.akka"  %% "akka-http-testkit"        % akkaHttpVersion % Test,
      "com.typesafe.akka"  %% "akka-actor-testkit-typed" % akkaVersion     % Test,
      "org.scalatest"      %% "scalatest"                % "3.2.9"         % Test
    ),
  )

