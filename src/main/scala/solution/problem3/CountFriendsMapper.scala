package solution.problem3

import org.apache.hadoop.io.{IntWritable, LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper
import io.circe.parser.parse
import io.circe.syntax._

class CountFriendsMapper extends Mapper[LongWritable, Text, Text, IntWritable] {
  override
  def map(key: LongWritable, value: Text, context: Mapper[LongWritable, Text, Text, IntWritable]#Context): Unit = {
    val data: (String, Int) = parse(value.toString) match {
      case Left(err) => ("", 0)
      case Right(json) => json.asArray match {
        case None => ("", 0)
        case Some(jsonArr) => (jsonArr.head.asString.getOrElse(""), 1)

      }
    }
    context.write(new Text(data._1), new IntWritable(data._2))
  }

}
