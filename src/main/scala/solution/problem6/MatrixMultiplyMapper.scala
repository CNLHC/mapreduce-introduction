package solution.problem6

import org.apache.hadoop.io.{IntWritable, LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper
import io.circe.parser.parse
import io.circe.syntax._

class MatrixMultiplyMapper extends Mapper[LongWritable, Text, Text, Text] {
  val MATRIX_SIZE = 10

  override
  def map(key: LongWritable, value: Text, context: Mapper[LongWritable, Text, Text, Text]#Context): Unit = {

    lazy val unit = ("", 0, 0, 0)
    val data = parse(value.toString).getOrElse(unit.asJson).as[Tuple4[String, Int, Int, Int]].getOrElse(unit)

    data._1 match {
      case "a" => (0 to 9).foreach(i =>
        context.write(new Text((data._2, i).toString), new Text(value.toString)))
      case "b" => (0 to 9).foreach(j =>
        context.write(new Text((j, data._3).toString), new Text(value.toString)))
      case _ => ()
    }

  }
}
