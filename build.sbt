name := "hackerrank"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies += "org.specs2" %% "specs2-core" % "3.9.1" % "test"
libraryDependencies += "org.specs2" %% "specs2-matcher-extra" % "3.9.1" % "test"

scalacOptions in Test ++= Seq("-Yrangepos")