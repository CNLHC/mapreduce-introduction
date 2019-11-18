package solution.problem4

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.{IntWritable, NullWritable, Text}
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat

object HadoopMain {

  def run(input: String, output: String): Unit = {
    val conf = new Configuration
    val job = new Job(conf, "asymmetric friends")

    job.setJarByClass(classOf[AsymmetricFriendsMapper])
    job.setMapperClass(classOf[AsymmetricFriendsMapper])

    job.setReducerClass(classOf[AsymmetricFriendsReducer])

    job.setMapOutputKeyClass(classOf[Text])
    job.setMapOutputValueClass(classOf[Text])

    job.setOutputKeyClass(classOf[NullWritable])
    job.setOutputValueClass(classOf[Text])

    FileInputFormat.addInputPath(job, new Path(input))
    FileOutputFormat.setOutputPath(job, new Path(output))

    job.waitForCompletion(true)
  }
}
