package solution.problem4

import io.circe.parser.parse
import io.circe.syntax._
import org.apache.spark.{SparkConf, SparkContext}


object SparkMain {
  def run(input: String, output: String): Unit = {
    val conf = new SparkConf().setAppName("inner join")
    lazy val unit = List("", "")
    val sc = new SparkContext(conf)
    (sc
      textFile (input)
      map (parse(_).getOrElse(unit.asJson).as[List[String]].getOrElse(unit))
      flatMap (v => List(((v.head, v.last), 1), ((v.last, v.head), 1)))
      reduceByKey (_ + _)
      filter (_._2 == 1)
      saveAsTextFile (output)
      )
  }
}
