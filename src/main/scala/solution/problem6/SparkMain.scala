package solution.problem6

import io.circe.parser.parse
import io.circe.syntax._
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


object SparkMain {
  def run(input: String, output: String): Unit = {
    val conf = new SparkConf().setAppName("inner join")
    lazy val unit = ("", 0, 0, 0)
    val sc = new SparkContext(conf)
    lazy val stream = (sc
      textFile (input)
      map (parse(_).getOrElse(unit.asJson).as[Tuple4[String, Int, Int, Int]].getOrElse(unit)))

    val a = (
      stream
        filter (_._1 == "a")
        flatMap (e => (0 to 10).map(v => ((e._2, v), e)))
      )

    val b = (
      stream
        filter (_._1 == "b")
        flatMap (e => (0 to 10).map(v => ((v, e._3), e)))
      )

    (a
      join (b)
      filter (v => v._2._1._3 == v._2._2._2)
      map (v => (v._1, v._2._1._4 * v._2._2._4))
      reduceByKey (_ + _)
      saveAsTextFile (output)
      )

  }
}
