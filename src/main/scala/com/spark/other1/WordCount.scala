package com.spark.other1

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Descripion TODO
 * @Author Solarzhou
 * @Date 2022/5/15 11:11  
 **/
object WordCount {
    def main(args: Array[String]): Unit = {
        // local 模式
        // 创建SparkConf 对象
        val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
        // 创建Spark上下文对象
        val sc: SparkContext = new SparkContext(sparkConf)
        println(sc)
        // 读取文件，将文件内容一行一行读出来
        val lines: RDD[String] = sc.textFile(args(0))
        // 根据空格分词
        val words: RDD[String] = lines.flatMap(_.split(" "))
        // 为方便计算， 将单词数据进行结构转换
        val wordToSum: RDD[(String, Int)] = words.map((_, 1))
        // 对转换后的数据进行分组聚合
        val wordToOne: RDD[(String, Int)] = wordToSum.reduceByKey(_ + _).sortBy(_._2, true)
        println(wordToOne.foreach(println))
        println("..........................")
        println(wordToOne.take(2))
        val result: Array[(String, Int)] = wordToOne.collect()
        //    println(result.toBuffer)
        //    result.foreach(println)


    }
}
