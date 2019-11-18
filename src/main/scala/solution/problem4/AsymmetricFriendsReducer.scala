package solution.problem4

import org.apache.hadoop.io.{NullWritable, Text}
import org.apache.hadoop.mapreduce.Reducer

import scala.collection.JavaConverters._
import io.circe.syntax._
import scala.collection.mutable

class AsymmetricFriendsReducer extends Reducer[Text, Text, NullWritable, Text] {
  override
  def reduce(key: Text, values: java.lang.Iterable[Text], context: Reducer[Text, Text, NullWritable, Text]#Context): Unit = {
    val ms: Map[String, Int] = values.asScala.map(new Text(_).toString).groupBy(identity).mapValues(_.size)
    ms.keys.filter(ms.getOrElse(_, -1) == 1).foreach(v => context.write(NullWritable.get(), new Text(
      List(key.toString, v).asJson.noSpaces
    )))
  }
}
