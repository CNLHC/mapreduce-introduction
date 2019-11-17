package solution.problem0

import java.util.StringTokenizer

import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.{Mapper};

class  WordCountMapper extends Mapper [Object, Text, Text, IntWritable] {
  val one = new IntWritable(1)
  val word = new Text()

  override def map(key: Object, value: Text, ctx: Mapper[Object, Text, Text, IntWritable]#Context) = {
    val itr = new StringTokenizer(value.toString)
    while (itr.hasMoreTokens()) {
      word.set(itr.nextToken())
      ctx.write(word, one)
    }
  }
}

