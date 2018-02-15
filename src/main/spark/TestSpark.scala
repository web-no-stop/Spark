import org.apache.spark.{SparkConf, SparkContext}

object TestSpark {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("go").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val rdd = sc.parallelize(List(1,2,3,4,5,6).map(_*3))
    val mappredRDD = rdd.filter(_ > 10).collect()
    println(rdd.reduce(_+_))
    for(arg <- mappredRDD)
      print(arg+" ")
    println()
    println("分布式计算结束")

  }
}
