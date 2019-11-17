package wordcount
import org.apache.hadoop.fs.{Path}
import org.apache.hadoop.io.{IntWritable,Text}

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.mapreduce.Job
import solution.problem0.WordCounterReducer
import solution.problem0.WordCountMapper
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat
import org.apache.hadoop.util.GenericOptionsParser



object  main{
  def main( args: Array[String]): Unit =
  {
    println("hello world in1")
    val conf = new Configuration
    val job = new Job(conf,"word,count")

    job.setJarByClass(classOf[WordCountMapper])
    job.setMapperClass(classOf[WordCountMapper])

    job.setCombinerClass(classOf[WordCounterReducer])
    job.setReducerClass(classOf[WordCounterReducer])

    job.setOutputKeyClass(classOf[Text])
    job.setOutputValueClass(classOf[IntWritable])

    FileInputFormat.addInputPath(job,new Path("in"))
    FileOutputFormat.setOutputPath(job,new Path("ou"))

    job.waitForCompletion(true)
  }

}