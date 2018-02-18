package examples
import org.apache.spark.sql.SparkSession
object CountItemsInDataSet {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("DataSet count items").getOrCreate()
    val examples = new Examples(spark, args(0))
    println("count: " + examples.countWords("he"))
//    println("longest sentence : " + examples.calcMuchWords())

  }
}
class Examples(val spark : SparkSession, val inputPath: String){
  val input_path: String = inputPath
  val sparkContext: SparkSession = spark
  def countWords(word : String): Long = {
    val textFile = spark.read.textFile(inputPath)
    val count = textFile.cache()
      .filter(line => line.contains(word))
      .count()
    return count
  }

  /*def calcMuchWords(): Long = {
    val textFile = spark.read.textFile(inputPath)
    val sentence = textFile.map(line => (line.split(" ").size)).reduce((a, b) => Math.max(a, b))
    return sentence
  }*/


}
