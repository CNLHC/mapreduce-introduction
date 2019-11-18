package solution.problem4

import org.apache.hadoop.io.{LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper
import io.circe.parser.parse

class AsymmetricFriendsMapper extends Mapper[LongWritable, Text, Text, Text] {
  override
  def map(key: LongWritable, value: Text, context: Mapper[LongWritable, Text, Text, Text]#Context): Unit = {
    val data = parse(value.toString) match {
      case Left(_) => ("", "")
      case Right(json) => json.asArray match {
        case None => ("", "")
        case Some(jsonArr) => (jsonArr.head.asString.getOrElse(""), jsonArr.last.asString.getOrElse(""))
      }
    }
    context.write(new Text(data._1), new Text(data._2))
    context.write(new Text(data._2), new Text(data._1))
  }
}
