#!/bin/bash
## 第一种
function f1() {
  s=0
for((i=0;i<=100;i++))
do
  s=$[$s+$i]
  done
  echo $s
}


## 第二种
function f2() {
    for i s $*
    do
      echo "dd:" $i
    done
}

## 第三种
function f3() {
    for i s "$*"
    do
      echo "third:" $i
    done
}

f3 $*