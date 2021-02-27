#!/bin/bash
#（1）必须在调用函数地方之前，先声明函数，shell脚本是逐行运行。不会像其它语言一样先编译。
#（2）函数返回值，只能通过$?系统变量获得，可以显示加：return返回，如果不加，将以最后一条命令运行结果，作为返回值。return后跟数值n(0-255)
str=""
function sum() {
    s=0
    s=$[$1+$2]
    str="$s"
    echo $str
    return $str
}


if [ $# -le 0 ]; then
    n1=100
    n2=200
else
  read -p "Please input the first number: " n1
  read -p "Please input the second number: " n2
fi
sum $n1 $n2
echo $?