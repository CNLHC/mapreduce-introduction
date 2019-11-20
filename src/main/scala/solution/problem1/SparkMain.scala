package solution.problem1

import org.apache.spark.{SparkConf, SparkContext}
import io.circe.parser.parse
import io.circe.syntax._


object SparkMain {
  def run(input: String,output: String): Unit ={
    val conf = new SparkConf().setAppName("invert index")
    lazy val unit = List("","")
    (new SparkContext(conf)
          textFile(input)
          map( parse(_).getOrElse(unit.asJson).as[List[String]].getOrElse(unit) )
          flatMap(v=>v.last.split("\\s").map((_,v.head)))
          distinct()
          reduceByKey(_+","+_)
          saveAsTextFile(output)

      )
  }
}
