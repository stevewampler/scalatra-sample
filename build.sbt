import AssemblyKeys._ // put this at the top of the file

import NativePackagerKeys._

packageArchetype.java_server

assemblySettings

scalariformSettings

organization := "io.swagger"

seq(webSettings :_*)

mainClass in assembly := Some("JettyMain")

name := "scalatra-sample"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.2"

scalacOptions += "-language:postfixOps"

val scalatestVersion = "2.2.6" // "2.2.1"
val scalatraVersion = "2.4.0" // "2.3.0.RC3"
val akkaVersion = "2.4.2" // "2.3.6"
val jettyVersion = "9.3.7.v20160115" // "9.2.3.v20140905"
val jettyServletVersion = "3.0.0.v201112011016"
val json4sVersion = "3.3.0" // "3.2.10"
val commonsCodecVersion = "1.10" // "1.7"
val dispatchVersion = "0.11.3" // 0.11.2

libraryDependencies ++= Seq(
  "org.slf4j"                % "slf4j-log4j12"                  % "1.7.18",
  "org.scalatest"           %% "scalatest"                      % scalatestVersion % "test",
  "org.scalatra"            %% "scalatra"                       % scalatraVersion,
  "org.scalatra"            %% "scalatra-scalate"               % scalatraVersion,
  "org.scalatra"            %% "scalatra-json"                  % scalatraVersion,
  "org.scalatra"            %% "scalatra-swagger"               % scalatraVersion,
  "org.scalatra"            %% "scalatra-swagger-ext"           % scalatraVersion,
  "org.scalatra"            %% "scalatra-slf4j"                 % scalatraVersion,
  "org.json4s"              %% "json4s-jackson"                 % json4sVersion,
  "org.json4s"              %% "json4s-ext"                     % json4sVersion,
  "commons-codec"            % "commons-codec"                  % commonsCodecVersion,
  "net.databinder.dispatch" %% "dispatch-core"                  % dispatchVersion,
  "net.databinder.dispatch" %% "dispatch-json4s-jackson"        % dispatchVersion,
  "com.typesafe.akka"       %% "akka-actor"                     % akkaVersion,
  "org.eclipse.jetty"        % "jetty-server"                   % jettyVersion % "container;compile;test",
  "org.eclipse.jetty"        % "jetty-webapp"                   % jettyVersion % "container;compile;test",
  "org.eclipse.jetty.orbit"  % "javax.servlet"                  % jettyServletVersion % "container;compile;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))
)

resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"

resolvers += "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

resolvers += "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/"

resolvers += "onesource"             at "http://artifactory.avention.com/artifactory/simple/onesource/"

//"clojars"               at "http://clojars.org/repo/",
//"clojure-releases"      at "http://build.clojure.org/releases",

resolvers += "Akka Maven Repository" at "http://repo.akka.io/releases/"

resolvers += "Typesafe Repo"         at "http://repo.typesafe.com/typesafe/releases/"

//ivyXML := <dependencies>
//    <exclude module="slf4j-log4j12"/>
//    <exclude module="grizzled-slf4j_2.9.1"/>
//    <exclude module="jsr311-api" />
//  </dependencies>

mergeStrategy in assembly <<= (mergeStrategy in assembly) {
  (old) => {
    case "about.html"     => MergeStrategy.discard
    case x => old(x)
  }
}

net.virtualvoid.sbt.graph.Plugin.graphSettings
