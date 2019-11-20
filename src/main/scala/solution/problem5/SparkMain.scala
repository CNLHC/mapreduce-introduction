package solution.problem5

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
      map (v => (v.last.dropRight(10), v.head))
      reduceByKey (_ + _)
      map (_._1)
      saveAsTextFile (output)
      )

  }
}
