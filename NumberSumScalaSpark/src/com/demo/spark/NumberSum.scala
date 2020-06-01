package com.demo.spark

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object NumberSum {
  def main(args: Array[String]): Unit = {
    
    //Create conf object
val conf = new SparkConf()
.setAppName("NumberSum")
conf.setMaster("local")
//create spark context object
val sc = new SparkContext(conf)
//Check whether sufficient params are supplied
if (args.length < 2) {
println("Usage: ScalaWordCount <input> <output>")
System.exit(1)
}
//Read file and create RDD
val rawData = sc.textFile(args(0))
//convert the lines into words using flatMap operation
val numbers = rawData.flatMap(line => line.split(" ")).filter(num=>(!num.isEmpty()))
val sum=numbers.map(num=>("sum",num.toInt)).reduceByKey(_+_)


//Save the result
sum.saveAsTextFile(args(1))
//stop the spark context
sc.stop
  }
}