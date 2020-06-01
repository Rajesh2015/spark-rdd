package com.demo.retail

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object ProductSales {
  def main(args: Array[String]): Unit = {
    //Create conf object
val conf = new SparkConf()
conf.setMaster("local")
.setAppName("ProductSales")
//create spark context object
val sc = new SparkContext(conf)
//Check whether sufficient params are supplied
if (args.length < 2) {
println("Usage: ProductSales <input> <output>")
System.exit(1)
}
//Read file and create RDD
val rawData = sc.textFile(args(0))
//convert the lines into words using flatMap operation
val f=(line:String)=>{
 var values=line.split("\t")
 (values(3),values(4).toFloat)
}
val product = rawData.map(f).reduceByKey(_+_)
//  //count the individual words using map and reduceByKey operation
//val wordCount = words.map(word => (word, 1)).reduceByKey(_ + _)
//Save the result
product.saveAsTextFile(args(1))
//stop the spark context
sc.stop
    
  }
  
}