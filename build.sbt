name := "api"

version := "1.0"

scalaVersion := "2.11.9"

resolvers +=
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies ++= Seq( "com.typesafe.akka" %% "akka-http" % "10.0.5",
                             "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.5")

mainClass in Compile := Some("WebServer")
