package com.spark.designRealizeBook1

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.log4j.Logger
import org.apache.log4j.Level
/**
 * @Descripion TODO
 * @Author Solarzhou
 * @Date 2022/5/15 11:08  
 **/
object Chapter7 {
    def main(args: Array[String]): Unit = {
        // local 模式
        // 创建SparkConf 对象
        Logger.getLogger("org").setLevel(Level.OFF)
        Logger.getLogger("akka").setLevel(Level.OFF)
        val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
        // 创建Spark上下文对象
        val sc: SparkContext = new SparkContext(sparkConf)
        val inputRDD: RDD[(Int, String)] = sc.parallelize(Array[(Int, String)](
            (1, "a"), (2, "b"), (3, "c"), (4, "d"), (5, "e"), (3, "f"), (2, "g"), (1, "h"), (2, "i")
        ), 3)
        val mappedRDD = inputRDD.map(r => (r._1 + 1, r._2))
        mappedRDD.cache()
        val reducedByKeyRDD = mappedRDD.reduceByKey((x, y) => x + "_" + y, 2)

        reducedByKeyRDD.cache()
        println("------------reducedByKeyRDD----------")
        reducedByKeyRDD.foreach(println)
    }
}
