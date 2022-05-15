package com.spark.designRealizeBook1

import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Descripion TODO
 * @Author Solarzhou
 * @Date 2022/5/15 11:08  
 **/
object Chapter7 {
    def main(args: Array[String]): Unit = {
        // local 模式
        // 创建SparkConf 对象
        val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
        // 创建Spark上下文对象
        val sc1: SparkContext = new SparkContext(sparkConf)
    }
}
