ThisBuild / scalaVersion := "2.12.10"
ThisBuild / organization := "xyz.cnworkshop"

updateOptions := updateOptions.value.withCachedResolution(true)


lazy val hello = (project in file("."))
  .settings(
    name := "DataLab",
    libraryDependencies ++= Seq(
      "org.apache.hadoop" % "hadoop-core" % "1.2.1",
    ),
    artifactName := { (sv: ScalaVersion, module: ModuleID, artifact: Artifact) =>
      "datalab.jar"
    },
    assemblyMergeStrategy in assembly := {
      case PathList("META-INF", xs @ _*) => MergeStrategy.discard
      case x => MergeStrategy.first
    }
)


