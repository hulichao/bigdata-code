package com.hoult.scala.json4s
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.native.JsonMethods._
import org.json4s.native.Serialization

object Demo1 {
  def main(args: Array[String]): Unit = {

    //parse方法表示从字符串到json-object
    val person = parse(
      """
        |{"name":"Toy","price":35.35}
        |""".stripMargin, useBigDecimalForDouble = true)
    // 1.模式匹配提取， \表示提取
    val JString(name) = (person \ "name")
    println(name)

    // 2.extract[String]取值
//    implicit val formats = org.json4s.Formats
    implicit val formats = DefaultFormats
    val name2 = (person \ "name").extract[String]
    val name3 = (person \ "name").extractOpt[String]
    val name4 = (person \ "name").extractOrElse("")

    // 3.多层嵌套取值
    val parseJson: JValue = parse(
      """
        |{"name":{"tome":"new"},"price":35.35}
        |""".stripMargin, useBigDecimalForDouble = true)

    //3.1 逐层访问
    val value = (parseJson \ "name" \ "tome").extract[String]
    //3.2 循环访问
    val value2 = (parseJson \\ "tome")
    println(value2)


    //4.嵌套json串解析
    val json = parse(
      """
         { "name": "joe",
           "children": [
             {
               "name": "Mary",
               "age": 20
             },
             {
               "name": "Mazy",
               "age": 10
             }
           ]
         }
      """)

//    println(json \ "children")

    //模式匹配
    for (JArray(child) <- json) println(child)

    //提取object 下 某字段的值
    val ages = for {
      JObject(child) <- json
      JField("age", JInt(age)) <- child
    } yield age

    println(ages)

    // 嵌套取数组中某个字段值，并添加过滤
    val nameAges = for {
      JObject(child) <- json
      JField("name", JString(name)) <- child
      JField("age", JInt(age)) <- child
      if age > 10
    } yield (name, age)

    println(nameAges)

    // 5.json和对象的转换,[就是json数组]
    case class ClassA(a: Int, b: Int)

    val json2: String = """[{"a":1,"b":2},{"a":1,"b":2}]"""

    val bb: List[ClassA] = parse(json2).extract[List[ClassA]]
    println(bb)

    // 6.json转对象，[json 非json数组，但是每个级别要明确]
    case class ClassC(a: Int, b: Int)

    case class ClassB(c: List[ClassC])

    val json3: String = """{"c":[{"a":1,"b":2},{"a":1,"b":2}]}"""

    val cc: ClassB = parse(json3).extract[ClassB]
    println(cc)

    // 7.使用org.json4s产生json字符串
//    import org.json4s.JsonDSL._
    val json1 = List(1, 2, 3)
    val jsonMap = ("name" -> "joe")
    val jsonUnion = ("name" -> "joe") ~ ("age" -> 10)
    val jsonOpt = ("name" -> "joe") ~ ("age" -> Some(1))
    val jsonOpt2 = ("name" -> "joe") ~ ("age" -> (None: Option[Int]))
    case class Winner(id: Long, numbers: List[Int])
    case class Lotto(id: Long, winningNumbers: List[Int], winners: List[Winner], drawDate: Option[java.util.Date])

    val winners = List(Winner(10, List(1, 2, 5)), Winner(11, List(1, 2, 0)))
    val lotto = Lotto(11, List(1, 2, 5), winners, None)

    val jsonCase =
      ("lotto" ->
        ("lotto-id" -> lotto.id) ~
          ("winning-numbers" -> lotto.winningNumbers) ~
          ("draw-date" -> lotto.drawDate.map(_.toString)) ~
          ("winners" ->
            lotto.winners.map { w =>
              (("winner-id" -> w.id) ~
                ("numbers" -> w.numbers))}))

    println(compact(render(json1)))
    println(compact(render(jsonMap)))
    println(compact(render(jsonUnion)))
    println(compact(render(jsonOpt)))
    println(compact(render(jsonOpt2)))
    println(compact(render(jsonCase)))

    // 8.json格式化
    println(pretty(render(jsonCase)))

    // 9.合并字符串
    val lotto1 = parse("""{
         "lotto":{
           "lotto-id": 1,
           "winning-numbers":[7,8,9],
           "winners":[{
             "winner-id": 1,
             "numbers":[7,8,9]
           }]
         }
       }""")

    val lotto2 = parse("""{
         "lotto":{
           "winners":[{
             "winner-id": 2,
             "numbers":[1,23,5]
           }]
         }
       }""")

    val mergedLotto = lotto1 merge lotto2
//    println(pretty(render(mergedLotto)))

    // 10.字符串寻找差异
    val Diff(changed, added, deleted) = mergedLotto diff lotto1
    println(changed)
    println(added)
    println(deleted)

    val json10 = parse(
      """

      """)

    println("********8")
    println(json10)
    for (JObject(j) <- json10) println(j)

    println("********11")

    // 11.遍历json flatMap
    // key1 values key1_vk1:v1 ....
    val str = "{\"tag_name\":\"t_transaction_again_day\",\"tag_distribute_json\":\"{\\\"1\\\":\\\"0.0011231395\\\",\\\"0\\\":\\\"0.9988768605\\\"}\"}"

    val valueJson = parse(str) \ "tag_distribute_json"
    println(valueJson)
    for {
      JString(obj) <- valueJson
      JObject(dlist) <- parse(obj)
      (key, JString(value))<- dlist
    } {
      println(key + "::" + value)
//      val kvList = for (JObject(key, value) <- parse(obj)) yield (key, value)
//      println("obj : " + kvList.mkString(","))
    }


    // 11.序列化，即对象转字符串
    val jsonMap2 = ("name" -> "joe")
    val str2 = Serialization.write(jsonMap2)
    println(str2)
  }
}
