import solution.problem0.HadoopMain.{run => P0Hadoop}
import solution.problem1.HadoopMain.{run => P1Hadoop}
import solution.problem2.HadoopMain.{run => P2Hadoop}
import solution.problem3.HadoopMain.{run => P3Hadoop}
import solution.problem4.HadoopMain.{run => P4Hadoop}
import solution.problem5.HadoopMain.{run => P5Hadoop}
import solution.problem6.HadoopMain.{run => P6Hadoop}
import solution.problem0.SparkMain.{run => P0Spark}
import solution.problem1.SparkMain.{run => P1Spark}
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.conf.Configuration





object Main {
  val  namenodeAddres= "hdfs://localhost:9000"
  def main(args: Array[String]): Unit = {
    println("Welcome to DataLab P1")

//    P0Hadoop("in", "ou")
//    P1Hadoop("in", "ou")
//    P2Hadoop("in", "ou")
//    P3Hadoop("in", "ou")
//    P4Hadoop("in", "ou")
//    P5Hadoop("in", "ou")
//    P6Hadoop("in", "ou")
//    P0Spark("hdfs://localhost:9000/user/root/BUAA/problem0/in/input","hdfs://localhost:9000/user/root/BUAA/problem0/ou/")
      P1Spark("/user/root/BUAA/problem1/in/input","hdfs://localhost:9000/user/root/BUAA/problem1/ou/")

  }
}
