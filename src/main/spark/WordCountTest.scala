import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object WordCountTest {
  def main(args: Array[String]): Unit = {
    if (args.length != 2) {

      sys.error("Usage: spark.example.WordCount <input> <output>")
      System.exit(1)
    }
    val input_path = args(0).toString
    val output_path = args(1).toString

    val conf = new SparkConf().setAppName("wordcount2")
    conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")

    val sc = new SparkContext(conf)
    val inputFile =  sc.textFile(input_path)

    inputFile.flatMap(line => line.split(" "))
        .map(word => (word, 1))
        .reduceByKey(_ + _)
        .map(x => x._1 + "\t" + x._2)
        .saveAsTextFile(output_path)
//    SparkSession.builder().appName("wordcount")
  }
}
