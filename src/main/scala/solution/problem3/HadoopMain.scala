package solution.problem3

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat

object HadoopMain {
  def run(input: String, output: String): Unit = {
    val conf = new Configuration
    val job = new Job(conf, "friends Count")

    job.setJarByClass(classOf[CountFriendsMapper])
    job.setMapperClass(classOf[CountFriendsMapper])

    job.setCombinerClass(classOf[CountFriendsReducer])
    job.setReducerClass(classOf[CountFriendsReducer])

    job.setOutputKeyClass(classOf[Text])
    job.setOutputValueClass(classOf[IntWritable])

    FileInputFormat.addInputPath(job, new Path(input))
    FileOutputFormat.setOutputPath(job, new Path(output))

    job.waitForCompletion(true)
  }


}
