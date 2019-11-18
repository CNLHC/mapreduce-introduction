package solution.problem1

import org.apache.hadoop._
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat

object HadoopMain {
  def run(input: String, output: String): Unit = {
    val conf = new Configuration
    val job = new Job(conf, "invert index")

    job.setJarByClass(classOf[InvertIndexMapper])
    job.setMapperClass(classOf[InvertIndexMapper])

    job.setCombinerClass(classOf[InvertIndexReducer])
    job.setReducerClass(classOf[InvertIndexReducer])

    job.setOutputKeyClass(classOf[Text])
    job.setOutputValueClass(classOf[Text])


    FileInputFormat.addInputPath(job, new Path(input))
    FileOutputFormat.setOutputPath(job, new Path(output))

    job.waitForCompletion(true)

  }
}
