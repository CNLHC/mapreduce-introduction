package solution.problem0

import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.Reducer

class Reducer extends Reducer[Text, IntWritable, Text, IntWritable] {
  override def reduce(key: Text, value: Iterable[IntWritable], ctx: Reducer[Text, IntWritable, Text, IntWritable]#context): Unit = {
    var sum = value.asScala().foldLeft(0)(_ + _.get)
    ctx.write(key, new IntWritable(sum))

  }

}
