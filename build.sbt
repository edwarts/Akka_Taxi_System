import sbtassembly.Plugin.AssemblyKeys
import AssemblyKeys._
import com.typesafe.sbt.SbtStartScript

name := "taxi_system"


version := "0.1-SNAPSHOT"

organization := "com.barclays"

scalaVersion := "2.11.1"

resolvers ++= Seq("Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
                  "Sonatype snapshots"  at "http://oss.sonatype.org/content/repositories/snapshots/",
                  "Spray Repository"    at "http://repo.spray.io",
                  "Spray Nightlies"     at "http://nightlies.spray.io/")

libraryDependencies ++= {
  val akkaVersion       = "2.3.4"
  val sprayVersion      = "1.3.1"
  Seq(
    "com.typesafe.akka" %% "akka-actor"      % akkaVersion,
    "com.typesafe.akka" % "akka-kernel_2.11" % akkaVersion,
    "com.typesafe.akka" % "akka-remote_2.11" % akkaVersion,
    "com.typesafe.akka" % "akka-camel_2.11" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j"      % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit"    % akkaVersion   % "test",
    "io.spray"          %% "spray-can"       % sprayVersion,
    "io.spray"          %% "spray-routing"   % sprayVersion,
    "io.spray"          %% "spray-json"      % "1.2.6",
    "ch.qos.logback"    %  "logback-classic" % "1.1.2",
    "org.scalatest"     %% "scalatest"       % "2.2.0"       % "test"
  )
}

// Assembly settings
mainClass in Global := Some("com.main.TaxiSystemRunner")

jarName in assembly := "goticks-server.jar"

assemblySettings

// StartScript settings
seq(SbtStartScript.startScriptForClassesSettings: _*)
