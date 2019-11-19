package solution.problem5

import org.apache.hadoop.io.{LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper
import io.circe.parser.parse


class UniqueTrimMapper extends Mapper[LongWritable, Text, Text, Text] {
  override
  def map(key: LongWritable, value: Text, context: Mapper[LongWritable, Text, Text, Text]#Context): Unit = {
    val data: (String, String) = parse(value.toString) match {
      case Left(_) => ("", "")
      case Right(json) => json.asArray match {
        case None => ("", "")
        case Some(jsonArr) => (jsonArr.head.asString.getOrElse(""), jsonArr.last.asString.getOrElse("").dropRight(10))
      }
    }
    context.write(new Text(data._2), new Text(data._1))
  }
}
