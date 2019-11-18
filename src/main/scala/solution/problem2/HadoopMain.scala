package solution.problem2

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.{IntWritable, NullWritable, Text}
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat

object HadoopMain {

  def run(input: String, output: String): Unit = {
    val conf = new Configuration
    val job = new Job(conf, "inner join")

    job.setJarByClass(classOf[InnerJoinMapper])
    job.setMapperClass(classOf[InnerJoinMapper])

//    job.setCombinerClass(classOf[InnerJoinReducer])
    job.setReducerClass(classOf[InnerJoinReducer])

    job.setOutputKeyClass(classOf[NullWritable])
    job.setMapOutputKeyClass(classOf[IntWritable])
    job.setOutputValueClass(classOf[Text])


    FileInputFormat.addInputPath(job, new Path(input))
    FileOutputFormat.setOutputPath(job, new Path(output))

    job.waitForCompletion(true)

  }
}
