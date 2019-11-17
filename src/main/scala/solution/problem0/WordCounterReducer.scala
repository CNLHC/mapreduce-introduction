package solution.problem0

import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.Reducer
import scala.collection.JavaConverters._

class WordCounterReducer extends Reducer[Text, IntWritable, Text, IntWritable] {
  override
  def reduce(key: Text, values: java.lang.Iterable[IntWritable],context:Reducer[Text,IntWritable,Text,IntWritable]#Context) {
    val sum = values.asScala.foldLeft(0)(_ + _.get)
    context.write(key, new IntWritable((sum)))
  }
}
