package solution.problem2

import org.apache.hadoop.io.{IntWritable, NullWritable, Text}
import org.apache.hadoop.mapreduce.Reducer
import io.circe.parser.parse
import io.circe.syntax._
import scala.collection.JavaConverters._

class InnerJoinReducer extends Reducer[IntWritable, Text, NullWritable, Text] {
  override
  def reduce(key: IntWritable, values: java.lang.Iterable[Text], context: Reducer[IntWritable, Text, NullWritable, Text]#Context): Unit = {
    val stringValues: List[List[String]] = values.asScala.map(e => parse(new Text(e).toString) match {
      case Left(err) => List("")
      case Right(json) => json.asArray match {
        case None => List("")
        case Some(jsonArr) => jsonArr.map(_.asString.getOrElse("")).toList
      }
    }).toList

    val first: List[String] = stringValues.filter(_.head == "order") match {
      case head :: xs => head
      case Nil => List()
    }

    stringValues
      .filter(_.head == "line_item")
      .foreach(e => context.write(NullWritable.get(),
        new Text(
          (first ::: e).asJson.toString().replaceAll("\\s", "")
        )))
  }
}
