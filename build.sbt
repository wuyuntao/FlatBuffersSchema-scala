
lazy val commonSettings = Seq(
  organization := "com.wuyuntao",
  version := "0.0.1",
  scalaVersion := "2.11.6",
  unmanagedBase := baseDirectory.value / ".." / "lib"
)

lazy val core = (project in file("flatbuffers-schema")).
  settings(commonSettings: _*).
  settings(
    name := "FlatBuffersSchema"
  )

lazy val tests = (project in file("flatbuffers-schema-tests")).
  aggregate(core).
  settings(commonSettings: _*).
  settings(
  	organization := "com.wuyuntao",
  	version := "0.0.1",
  	scalaVersion := "2.11.6",
  	unmanagedBase := baseDirectory.value / ".." / "lib",

    name := "FlatBuffersSchemaTests",

    libraryDependencies ++= Seq(
	  "junit" % "junit" % "4.8.1" % "test",
      "org.specs2" % "specs2-core" % "3.6.1" % "test"
	), 	
	resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
	scalacOptions in Test ++= Seq("-Yrangepos")
  )