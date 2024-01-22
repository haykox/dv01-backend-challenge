lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """dv01-backend-challenge""",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.12.9",
    libraryDependencies ++= Seq(
      guice,
      "com.h2database" % "h2" % "1.4.199",
      "com.github.tototoshi" %% "scala-csv" % "1.3.5",
      "io.circe" %% "circe-generic" % "0.14.5",
      "io.circe" %% "circe-generic-extras" % "0.14.3",
      "io.circe" %% "circe-parser" % "0.14.5",
      "org.typelevel" %% "cats-core" % "2.9.0",
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    )
  )
