package com.hoult.scala.hello4


class Person {
  //声明字段必须进行初始化，scala编译器会根据初始化的数据类型自动推断
  var name = "hoult"

  //_表示占位符，编译器会根据变量的数据类型赋予相应的初始值
  //注意是var 不是val
  var age: Int = _

  def age_(ne: Int) = {
    age = ne
  }

  //注意null 要特别赋值的化，要i指定类型，否则会被任务是Null类似 val add: NUll = null
  val add: String = null

  //类中的私有字段，有私有的gettter 和 setter 字段
  //可以在类的内部访问，一个可以让其伴生对象访问
  private var hobby = "旅游"
  //对象私有字段，访问权限更小，只能在当前类中访问
  private [this] var cardInfo = "1000"

  def hello(mesage: String) = {
    println(s"$mesage, $cardInfo")
  }
}
object ClassDemo {
  def main(args: Array[String]): Unit = {
    val person = new Person
    //小括号可以省略

    person.age = 50

    println(person.age)
    //如果对象的属性加上_=给var修饰的属性进行复制，其实就是调用age_=这个setter方法
    person.age_= (20)

    println(person.age)
  }

}
