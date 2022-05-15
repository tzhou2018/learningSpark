package com.spark.designRealizeBook1.cache

import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel
import org.apache.log4j.Logger
import org.apache.log4j.Level

object CacheTest {
    def main(args: Array[String]) {
        Logger.getLogger("org").setLevel(Level.OFF)
        Logger.getLogger("akka").setLevel(Level.OFF)
        val spark = SparkSession
            .builder()
            .appName("Cache Test")
            .master("local[2]")
            .getOrCreate()

        val sc = spark.sparkContext

        var inputRDD = sc.parallelize(Array[(Int, String)](
            (1, "a"), (2, "b"), (3, "c"), (4, "d"), (5, "e"), (3, "f"), (2, "g"), (1, "h"), (2, "i")
        ), 3)

        //    println("------------input rdd----------")
        //    inputRDD.mapPartitionsWithIndex((pid, iter)=>{
        //      iter.map( value => "PID: " + pid + ", value: " + value)
        //    }).foreach(println)
        val mappedRDD = inputRDD.map(r => (r._1 + 1, r._2))
         mappedRDD.persist(StorageLevel.MEMORY_AND_DISK_SER)
//        mappedRDD.cache()

        //    val resultRDD = inputRDD.reduceByKey((x,y) => x+y,2)
        val reducedByKeyRDD = mappedRDD.reduceByKey((x, y) => x + "_" + y, 2)
        val groupedByKeyRDD = mappedRDD.groupByKey().mapValues(v => v.toList)


        println("------------reducedByKeyRDD----------")
        reducedByKeyRDD.mapPartitionsWithIndex((pid, iter) => {
            iter.map(value => "PID: " + pid + ", value: " + value)
        }).foreach(println)

        // mappedRDD.unpersist()
        println("------------groupedByKeyRDD----------")
        groupedByKeyRDD.mapPartitionsWithIndex((pid, iter) => {
            iter.map(value => "PID: " + pid + ", value: " + value)
        }).foreach(println)


        println(reducedByKeyRDD.toDebugString)
        println(groupedByKeyRDD.toDebugString)


        System.in.read()

        spark.stop()
    }
}
