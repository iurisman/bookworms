addSbtPlugin("io.spray" % "sbt-revolver" % "0.9.1")
addSbtPlugin("com.github.tototoshi" % "sbt-slick-codegen" % "1.4.0")

libraryDependencies ++= Seq(
  "org.postgresql"     % "postgresql"                % "42.6.0"
)