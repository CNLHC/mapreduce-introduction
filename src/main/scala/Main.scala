import solution.problem0.HadoopMain.{run => P0Hadoop}
import solution.problem1.HadoopMain.{run => P1Hadoop}
import solution.problem2.HadoopMain.{run => P2Hadoop}
import solution.problem3.HadoopMain.{run => P3Hadoop}
import solution.problem4.HadoopMain.{run => P4Hadoop}
import solution.problem5.HadoopMain.{run => P5Hadoop}
import solution.problem6.HadoopMain.{run => P6Hadoop}
import solution.problem0.SparkMain.{run => P0Spark}
import solution.problem1.SparkMain.{run => P1Spark}
import solution.problem2.SparkMain.{run => P2Spark}
import solution.problem3.SparkMain.{run => P3Spark}
import solution.problem4.SparkMain.{run => P4Spark}
import solution.problem5.SparkMain.{run => P5Spark}
import solution.problem6.SparkMain.{run => P6Spark}


object Main {
  val namenodeAddres = "hdfs://localhost:9000"

  def hadoopInput = (no: Int) => s"$namenodeAddres/user/root/BUAA/problem$no/in/"

  def sparkInput = (no: Int) => s"$namenodeAddres/user/root/BUAA/problem$no/in/input"

  def output = (no: Int) => s"$namenodeAddres/user/root/BUAA/problem$no/ou/"


  def main(args: Array[String]): Unit = {
    val s = args.mkString(",")
    println(s)
    args(0) match {
      case "hadoop" =>
        args(1) match {
          case "0" => P0Hadoop(hadoopInput(0), output(0))
          case "1" => P1Hadoop(hadoopInput(1), output(1))
          case "2" => P2Hadoop(hadoopInput(2), output(2))
          case "3" => P3Hadoop(hadoopInput(3), output(3))
          case "4" => P4Hadoop(hadoopInput(4), output(4))
          case "5" => P5Hadoop(hadoopInput(5), output(5))
          case "6" => P6Hadoop(hadoopInput(6), output(6))
          case _ => println("no problem find")
        }
      case "spark" =>
        args(1) match {
          case "0" => P0Spark(sparkInput(0), output(0))
          case "1" => P1Spark(sparkInput(1), output(1))
          case "2" => P2Spark(sparkInput(2), output(2))
          case "3" => P3Spark(sparkInput(3), output(3))
          case "4" => P4Spark(sparkInput(4), output(4))
          case "5" => P5Spark(sparkInput(5), output(5))
          case "6" => P6Spark(sparkInput(6), output(6))
          case _ => println("no problem find")
        }
      case _ => println("usage: <hadoop>|<spark> ")
    }
  }
}
