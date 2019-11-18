package solution.problem2

import org.apache.hadoop.io.{IntWritable, LongWritable, NullWritable, Text}
import org.apache.hadoop.mapreduce.Mapper
import io.circe.parser.parse

class InnerJoinMapper extends Mapper[LongWritable, Text, IntWritable, Text] {
  override
  def map(key: LongWritable, value: Text, context: Mapper[LongWritable, Text, IntWritable, Text]#Context): Unit = {
    val data: (Int, String) = parse(value.toString) match {
      case Left(err) => (-1, "")
      case Right(json) => json.asArray match {
        case None => (-1, "")
        case Some(jsonArr) => (jsonArr(1).asString.getOrElse("-1").toInt, json.noSpaces)
      }
    }
    context.write(new IntWritable(data._1), new Text(data._2))
  }
}
