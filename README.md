# mapreduce introduction

This project use Hadoop streaming and Spark RDD to solve seven basic MapReduce problems. 
All examples were write by scala. To Run these example, please checkout [this](#build-and-run)

## Covered problems

seven problems include :

0. words count
1. inverted index
2. inner join
3. friends count
4. asymmetric relationship detection
5. trimmed dna slice 
6. sparse matrix multiply

please refer to [Assignments.pdf](./problems/Assignments.pdf) for more information.

in the [problems](./problems) folder, we provide python code for each problems as conceptual demonstration of how 
MapReduce work.

## build and run

### build 

be sure you have sbt installed and then 

1. cwd to the root of this repo
2. launch sbt shell using command `sbt`
3. waiting for sbt to resolve all build dependencies 
(This may take very long ( sometimes 30min or more ) and have no echo at all.
 please consider configuring your sbt to use local maven mirror to speed up  this process )
4. use  `assembly` to generate fat jar.

after doing these correctly, the file `DataLab-assembly-0.1.0-SNAPSHOT.jar` should appear in `target/scala-2.12/`directory,

### run

using docker is the most convenient way to run these examples. I recommend you use a image named "hadoop-spark-pig-hive"
 as runtime.
 
1. pull the image from dockerhub
 
```bash
docker pull suhothayan/hadoop-spark-pig-hive
```
    
2. create container using this image

```bash
docker run\  
       -it\
       -v  <PATH TO THIS REPO>:/artifact\
       --name mapreduce-introduction
       --network host\
       suhothayan/hadoop-spark-pig-hive:2.9.2\
       bash
```
 if everything is correct, you should attach to the container shell and 
 this repo can be found in the `/artifact` directory.

3. init hdfs

```bash
cd /artifact
chmod +x init.sh run.sh
./init.sh
```

`init.sh` is a simple tool help us put test data sets into hdfs.

4. run example 

```
# solve problem 0 using hadoop stream
./run.sh hadoop 0

# solve problem 6 using spark RDD
./run.sh spark 6
```

After job complete, a file named like `result-0` should appear in the current work path.


