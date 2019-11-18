package solution.problem1

import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Reducer
import scala.collection.JavaConverters._


class InvertIndexReducer extends Reducer[Text, Text, Text, Text] {
  override
  def reduce(key: Text, values: java.lang.Iterable[Text], context: Reducer[Text, Text, Text, Text]#Context): Unit = {
    context.write(key, new Text(values.asScala.map(new Text(_).toString).toSet.foldLeft("")(_ + _ + " ")))
  }
}
