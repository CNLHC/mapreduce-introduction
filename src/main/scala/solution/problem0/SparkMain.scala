package solution.problem0
import org.apache.spark.{SparkConf, SparkContext}
object SparkMain {
  def run(input: String,output: String): Unit ={
    val conf = new SparkConf().setAppName("word count")
    (new SparkContext(conf)
         textFile(input)
         flatMap(_.split(" "))
         map ((_,1))
         reduceByKey(_+_)
         saveAsTextFile(output))
  }
}
