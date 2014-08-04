package idchange

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.SparkContext._

/**
 * Created by wlu on 2014/7/22.
 */
object IdChangePlay extends App {
    val sparkConfig = new SparkConf().setAppName("Id Change").setMaster("local").set("spark.logConf", "true")
    val sc = new SparkContext(sparkConfig)
    // idchange file RDD
    val file = sc.textFile(args(0))
    // org id change (key type + from id), to id

    var counter = -1
    var orgIdChange = file.map {
        line =>
            val splits = line.split(" ")
            (splits(0).toInt, splits(1).toInt)
    }

    def tc(p: (Int, Iterable[Int])): Iterable[(Int, Int)] = {
        val fromK = p._1
        val toKs = p._2
        val _min = toKs.min
        val _max = toKs.max

        if (fromK <= _min) {
            toKs.map(i => (fromK, i))
        } else if (fromK >= _max) {
            // do nothing
            List()
        } else {
            // need transitive closure
            counter = counter + 1
            toKs.filter { i => i != _min}.map {
                i => (_min, i)
            }
        }
    }

    while (counter != 0) {
        // init counter
        counter = 0
        // reverse id change
        val revIdChange = orgIdChange.map {
            p => (p._2, p._1)
        }

        val merged = (orgIdChange ++ revIdChange).distinct

        val gpKey = merged.groupByKey

        val newChange = gpKey.flatMap {tc}
        newChange.collect

        orgIdChange = newChange

    }

    orgIdChange.groupByKey().collect().foreach {
        p => println(s"(${p._1}, ${p._2})")
    }
}
