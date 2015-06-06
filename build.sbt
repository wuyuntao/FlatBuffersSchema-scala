
lazy val commonSettings = Seq(
  organization := "com.wuyuntao",
  version := "0.0.1",
  scalaVersion := "2.11.6",
  unmanagedBase := baseDirectory.value / "lib"
)

lazy val core = (project in file("flatbuffers-schema")).
  settings(commonSettings: _*).
  settings(
    name := "FlatBuffersSchema",

    libraryDependencies ++= Seq(
      "junit" % "junit" % "4.11" % "test",
      "com.novocode" % "junit-interface" % "0.11" % "test"
    )  
  )
