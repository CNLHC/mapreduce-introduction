import solution.problem0.HadoopMain.{run => P0Hadoop}
import solution.problem1.HadoopMain.{run => P1Hadoop}
import solution.problem2.HadoopMain.{run => P2Hadoop}
import solution.problem3.HadoopMain.{run => P3Hadoop}
import solution.problem4.HadoopMain.{run => P4Hadoop}

object main {
  def main(args: Array[String]): Unit = {
    println("Welcome to DataLab P4")

    P0Hadoop("in", "ou")
    P1Hadoop("in", "ou")
    P2Hadoop("in", "ou")
    P3Hadoop("in", "ou")
    P4Hadoop("in", "ou")
  }

}
