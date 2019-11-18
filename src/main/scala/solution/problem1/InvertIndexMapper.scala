package solution.problem1

import org.apache.hadoop.io.{LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper
import io.circe.parser.parse
import org.apache.commons.logging.LogFactory


class InvertIndexMapper extends Mapper[LongWritable, Text, Text, Text] {

  override
  def map(key: LongWritable, value: Text, context: Mapper[LongWritable, Text, Text, Text]#Context): Unit = {
    val data: (String, String) = parse(value.toString) match {
      case Left(err) => ("", "")
      case Right(json) => json.asArray match {
        case None => ("", "")
        case Some(arr) => (arr.head.asString.getOrElse(""), arr.last.asString.getOrElse(""))
      }
    }

    data._2.split("\\s").foreach { r => context.write(new Text(r), new Text(data._1)) }
  }
}

