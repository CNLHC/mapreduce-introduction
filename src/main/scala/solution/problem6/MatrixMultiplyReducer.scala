package solution.problem6

import org.apache.hadoop.io.{NullWritable, Text}
import org.apache.hadoop.mapreduce.Reducer
import io.circe.parser.parse
import io.circe.syntax._
import org.apache.hadoop.io

import scala.collection.JavaConverters._


class MatrixMultiplyReducer extends Reducer[Text, Text, NullWritable, Text] {
  override
  def reduce(key: Text, values: java.lang.Iterable[Text], context: Reducer[Text, Text, NullWritable, Text]#Context): Unit = {

    //    context.write(NullWritable.get(), new Text(values.asScala.foldLeft("")((a, c) => a + new Text(c).toString)))
    //  }
    //    val idx = key.toString.replaceAll("\\(|\\)", "").split(",").map(_.toInt)
    lazy val unit = ("", 0, 0, 0)

    val data: Map[String, Iterable[(String, Int, Int, Int)]] = values.asScala.map(e => {
      val value = new io.Text(e).toString
      val data = parse(value.toString).getOrElse(unit.asJson).as[Tuple4[String, Int, Int, Int]].getOrElse(unit)
      (data._1, data)
    }).groupBy(_._1).mapValues(_.map(_._2))


    val aRow = data.get("a")
    val bRow = data.get("b")

    var result = 0
    (aRow, bRow) match {
      case (Some(a), Some(b)) => a.foreach(e => b.foreach(v => {
        if (e._3 == v._2)
          result += e._4 * v._4
      }))
      case _ => ()
    }

    if (result != 0)
      context.write(NullWritable.get(), new Text(
        (key.toString, result).asJson.noSpaces
      ))

  }
}
