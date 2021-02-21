package demo

//类参数和属性
class Test(var name: String, var age: String) {
  println(name)
}
object ScalaDemo {

  def a(): String = {
    println("test aaa")
    return "result aaa"
  }

  def b(): String = {
    println("test bbb")
    return "result bbb"
  }

  lazy val c = () => {
    println("test ccc")
  }

  def main(args: Array[String]): Unit = {
    val name = Map("a" -> a(), "b" -> b(), "c" -> c())
    println("666")
    println(name("a"))
  }

}
