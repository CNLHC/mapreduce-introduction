import solution.problem0.HadoopMain.{run => P0Hadoop}
import solution.problem1.HadoopMain.{run => P1Hadoop}

object main {
  def main(args: Array[String]): Unit = {
    println("Welcome to DataLab")
    P0Hadoop("in", "ou")
    P1Hadoop("in", "ou")
  }

}
