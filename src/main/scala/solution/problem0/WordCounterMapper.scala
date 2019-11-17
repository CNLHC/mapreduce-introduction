package solution.problem0


import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.{Mapper};

class  WordCountMapper extends Mapper [Object, Text, Text, IntWritable] {
  val one = new IntWritable(1)
  val word = new Text()

  override def map(key: Object, value: Text,context: Mapper[Object, Text, Text, IntWritable]#Context) = {
    for (t <-  value.toString().split("\\s")) {
      System.out.println("1")
      word.set(t)
      context.write(word, one)
    }
  }
}

