package solution.problem2

import io.circe.parser.parse
import io.circe.syntax._
import org.apache.spark.{SparkConf, SparkContext}


object SparkMain {
  def run(input: String, output: String): Unit = {
    val conf = new SparkConf().setAppName("inner join")
    lazy val unit = List("", "")
    val sc = new SparkContext(conf)

    val order = (sc
      textFile (input)
      map (parse(_).getOrElse(unit.asJson).as[List[String]].getOrElse(unit))
      filter (_.head=="order")
      map (v => (v(1), v.mkString(",")))
      )
    val line = (sc
      textFile (input)
      map (parse(_).getOrElse(unit.asJson).as[List[String]].getOrElse(unit))
      filter (_.head=="line_item")
      map (v => (v(1), v.mkString(",")))
      )
    order.join(line).saveAsTextFile(output)
  }
}
