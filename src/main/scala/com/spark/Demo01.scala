package com.spark

import org.apache.spark.{SparkConf, SparkContext}

object Demo01 {

    def main(args: Array[String]): Unit = {

        //1.创建SparkConf并设置App名称
        val conf = new SparkConf().setAppName("WC")

        //2.创建SparkContext，该对象是提交Spark App的入口
        val sc = new SparkContext(conf)

        //3.使用sc创建RDD并执行相应的transformation和action
        val value = sc.textFile("data").flatMap(_.split(" ")).map((_, 1)).
            reduceByKey(_ + _, 1)
        println(value.take(2))
        //    println(value.collect().foreach(println(_)))
        println(value.collect().take(5))
        //4.关闭连接
        sc.stop()
    }
}
//class Test02{
//  private val test0 = new Test02
////  Demo01
//}