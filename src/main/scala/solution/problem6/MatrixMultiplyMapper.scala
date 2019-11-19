package solution.problem6

import org.apache.hadoop.io.{IntWritable, LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper
import io.circe.parser.parse

class MatrixMultiplyMapper extends Mapper[LongWritable, Text, Text, Text] {
  val MATRIX_SIZE = 10

  override
  def map(key: LongWritable, value: Text, context: Mapper[LongWritable, Text, Text, Text]#Context): Unit = {
    parse(value.toString) match {
      case Left(_) => ()
      case Right(json) => json.asArray match {
        case None => ()
        case Some(jsonArr) => jsonArr.head.asString.getOrElse("") match {
          case "a" => (0 to 9).foreach(i =>
            context.write(new Text((i, jsonArr(1).asNumber.getOrElse(-1)).toString), new Text(value.toString)))
          case "b" => (0 to 9).foreach(j =>
            context.write(new Text((jsonArr(1).asNumber.getOrElse(-1), j).toString), new Text(value.toString)))
          case _ => ()
        }
      }
    }
  }
}
