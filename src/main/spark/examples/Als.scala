package examples

import org.apache.spark.mllib.recommendation.Rating
import org.apache.spark.sql.SparkSession

object Als {
  def countRatingScore(ratePath : String, userPath : String, spark: SparkSession) = {
    import spark.implicits._
    val ratings = spark.read.textFile(ratePath).map {
      line =>
        val fields = line.split("\t")
        (fields(3).toLong % 10, Rating(fields(0).toInt, fields(1).toInt, fields(2).toDouble))
    }.cache()
    val movies = spark.read.textFile(userPath).map {
      line => val fields = line.split("|")
        (fields(0).toInt, fields(1))
    }
    //总共电影数量
    val totalMovie =  movies.count()
    //用户数量
    val userNum = ratings.map(_._2.user).distinct().count()
    //评分电影数量
    val ratingMovieNum = ratings.map(_._2.product).distinct().count()
    //评分数量
    val ratingNum = ratings.count()

    println("there are " + ratingNum + " comments from " + userNum + " users on " + ratingMovieNum + " movies")

  }

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("als")
      .master("local[*]")
      .config("num-executors", 3)
      .getOrCreate()
    countRatingScore("D:\\myself\\大数据\\八斗\\课件\\代码\\spark\\u1.test", "D:\\myself\\大数据\\八斗\\课件\\代码\\spark\\u.item", spark)
  }
}
