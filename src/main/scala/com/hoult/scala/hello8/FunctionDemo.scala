package com.hoult.scala.hello8

//方法不能作为单独的表达式而存在，而函数可以；
//函数必须要有参数列表，而方法可以没有参数列表；
//方法名是方法调用，而函数名只是代表函数对象本身；
//在需要函数的地方，如果传递一个方法，会自动把方法转换为函数
object FunctionDemo {
  def main(args: Array[String]): Unit = {
    // 下面用三种方式定义了函数，其中第二种方式最常见
    val adder1: (Int, Int) => Int = (x, y) => x+y
    val adder2 = (x: Int, y: Int) => x+y
    // Function2是特质，不能直接new
    // new Function2[Int,Int,Int]{ ... } 其实是定义并实例化一个实现了 Function2 特质的类的对象
    val adder3 = new Function2[Int, Int, Int]{
      def apply(x: Int, y: Int): Int = {
        x + y
      }
    }
  }


}
