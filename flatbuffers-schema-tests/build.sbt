name := "FlatBuffersSchemaTests"
version := "0.0.1"
scalaVersion := "2.11.6"

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "3.6.1" % "test")
libraryDependencies ++= Seq("junit" % "junit" % "4.8.1" % "test")

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

scalacOptions in Test ++= Seq("-Yrangepos")