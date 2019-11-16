"""
@Author: WangCaimeng
@Date: 2019-09-18 18:49:15
@LastEditors: WangCaimeng
@LastEditTime: 2019-09-18 18:49:15
"""
from __future__ import print_function
from pyspark import SparkContext
import json
sc = SparkContext( 'local', 'test')
textFile = sc.textFile("file:///root/BigData/wordcount//words.json")
wordCount = textFile.flatMap(lambda row:json.loads(row)[1].split(' ')).map(lambda word:(word,1)).reduceByKey(lambda a,b:a+b)
wordCount.foreach(print)