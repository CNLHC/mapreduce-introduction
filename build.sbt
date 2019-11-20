ThisBuild / scalaVersion := "2.12.10"
ThisBuild / organization := "xyz.cnworkshop"

updateOptions := updateOptions.value.withCachedResolution(true)
val circeVersion = "0.11.1"



lazy val hello = (project in file("."))
  .settings(
    name := "DataLab",
    libraryDependencies ++= Seq(
      "org.apache.hadoop" % "hadoop-core" % "1.2.1",
      "org.apache.hadoop" % "hadoop-client" % "2.9.2",
      "org.apache.spark" %"spark-core_2.12" % "2.4.4",
      "io.circe" %% "circe-core" % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "io.circe" %% "circe-parser" % circeVersion
    ),
    artifactName := { (sv: ScalaVersion, module: ModuleID, artifact: Artifact) =>
      "datalab.jar"
    },
    assemblyMergeStrategy in assembly := {
      case PathList("META-INF", xs @ _*) => MergeStrategy.discard
      case x => MergeStrategy.first
    }
)


