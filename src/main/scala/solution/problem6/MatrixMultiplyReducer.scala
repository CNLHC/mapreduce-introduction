package solution.problem6

import org.apache.hadoop.io.{NullWritable, Text}
import org.apache.hadoop.mapreduce.Reducer
import io.circe.parser.parse
import io.circe.syntax._
import scala.collection.JavaConverters._


class MatrixMultiplyReducer extends Reducer[Text, Text, NullWritable, Text] {
  override
  def reduce(key: Text, values: java.lang.Iterable[Text], context: Reducer[Text, Text, NullWritable, Text]#Context): Unit = {

    //    context.write(NullWritable.get(), new Text(values.asScala.foldLeft("")((a, c) => a + new Text(c).toString)))
    //  }
    val idx = key.toString.replaceAll("\\(|\\)", "").split(",").map(_.toInt)
    val data: Map[String, Iterable[(String, Int, Int, Int)]] = values.asScala.map(e => parse(new Text(e).toString) match {
      case Left(_) => ("", -1, -1, -1)
      case Right(json) => json.asArray match {
        case None => ("", -1, -1, -1)
        case Some(jsonArr) =>
          val a = ((
            jsonArr(0).asString.getOrElse(""),
            jsonArr(1).asNumber.getOrElse(1),
            jsonArr(2).asNumber.getOrElse(1),
            jsonArr(3).asNumber.getOrElse(1),
          ))
          return a
      }
    }).filter(t => t._1 == "a" || t._1 == "b")
      .groupBy(_._1)


    val aRow = data.get("a")
    val bRow = data.get("b")
//    context.write(NullWritable.get(), new Text(aRow.foldLeft("")((a, c) => a + new Text(c.toString()).toString)))
      var result = 0
      (aRow, bRow) match {
        case (Some(a), Some(b)) => a.foreach(e => b.foreach(v => {
          if (e._3 == v._2)
            result += e._4 * v._4
        }))
        case _ => ()
      }

      context.write(NullWritable.get(), new Text(
        List(idx.head, idx.last, result).asJson.noSpaces
      ))

    }
}
