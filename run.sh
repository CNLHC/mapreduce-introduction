if [ "$1" = "hadoop" ]; then
  echo using hadoop
  hadoop jar /artifact/target/scala-2.12/DataLab-assembly-0.1.0-SNAPSHOT.jar $1 $2
elif [ "$1" = "spark" ]; then
  echo using spark
  spark-submit /artifact/target/scala-2.12/DataLab-assembly-0.1.0-SNAPSHOT.jar $1 $2
else
  echo "wrong method, use hadoop or spark"
  exit -1
fi

hadoop fs -getmerge BUAA/problem$2/ou result-$2
hadoop fs -rm -r BUAA/problem$2/ou
