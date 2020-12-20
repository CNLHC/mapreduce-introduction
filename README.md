# mapreduce introduction

This project is the final project of `大数据导论2019` of BUAA SCSE.

This project used `Hadoop streaming` and `Spark RDD` to solve seven basic MapReduce problems. 
All the examples were written in scala. To run them, please read [this simple guide](#build-and-run).

## Covered problems

0. words count
1. inverted index
2. inner join
3. friends count
4. asymmetric relationship detection
5. trimmed dna slice 
6. sparse matrix multiply

Refer to [Assignments.pdf](./problems/Assignments.pdf) for more information.

In the [problems](./problems) folder, I provided python code for each problems as conceptual demonstration of how 
MapReduce work.

## build and run

### build 

Be sure you have sbt installed and then 

1. `cd` to the root of this repo.
2. launch sbt shell using command `sbt`
3. waiting for sbt to resolve all build dependencies 
>This may take very long time (sometimes 30min or more) and have no echo at all. Configuring your sbt to use local maven mirrors can speed up this process.
4. using  `assembly` command to generate a fat jar.

The file `DataLab-assembly-0.1.0-SNAPSHOT.jar` should appear in `target/scala-2.12/` directory.

### run

Using docker is the most convenient way to run these examples. 
 
1. pull image from dockerhub
 
```bash
docker pull suhothayan/hadoop-spark-pig-hive:2.9.2
```
    
2. create container using this image

```bash
# if you are the root of this repo
docker run \  
       -it \
       -v  $(pwd):/artifact \
       --name mapreduce-introduction \
       --network host \
       suhothayan/hadoop-spark-pig-hive:2.9.2 \
       bash
```
if everything goes correct, you should attach to the container shell now. 
And this repo can be found at `/artifact` directory. 


3. init hdfs

```bash
cd /artifact
chmod +x init.sh run.sh
./init.sh
```

`init.sh` is a simple tool that help us put test datasets into hdfs.

4. run example 

Remember we `assembled` a fat jar just now? you can run it in this docker. 

```
# solve problem 0 using hadoop stream
./run.sh hadoop 0

# solve problem 6 using spark RDD
./run.sh spark 6
```

After the job complete, a file like `result-0` should appear in the current work path.


