package solution.problem5

import org.apache.hadoop.io.{NullWritable, Text}
import org.apache.hadoop.mapreduce.Reducer

class UniqueTrimReducer extends Reducer[Text, Text, NullWritable, Text] {
  override
  def reduce(key: Text, value: java.lang.Iterable[Text], context: Reducer[Text, Text, NullWritable, Text]#Context): Unit = {
    context.write(NullWritable.get(), key)
  }

}
