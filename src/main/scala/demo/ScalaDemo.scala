package demo

//类参数和属性
class Test(var name: String, var age: String) {
  println(name)
}
object ScalaDemo {
  def main(args: Array[String]): Unit = {
    println("hello")
    val test = new Test("1", "2")
    test.age = "10"
  }

}
