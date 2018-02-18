object ImplicitTest {
  /********************implicit parameters*********************/
  object Context {
    implicit val rate: Float = 5.0f
    implicit val rate2: Float = 2.0f
  }
  def calcTax(amount: Float)(implicit rate: Float): Float = amount * rate
  /********************implicit parameters*********************/

  /********************implicit transfer*********************/
  implicit def double2Int (d: Double) = d.toInt
  /********************implicit transfer*********************/

  /********************implicit function*********************/

  def main(args: Array[String]): Unit = {
    import Context._
    println(calcTax(50000F)(Context.rate2))
    //使用了 上面的double2Int隐式转换类型
    val i : Int = 3.5
  }
}
