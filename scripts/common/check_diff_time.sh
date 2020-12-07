#!/bin/bash
#
first_stamp=` date  +%s`  #计算开始的时间戳
## 中间增加脚本
today_stamp=` date  +%s`  #计算结束的时间戳
let  day_stamp=($today_stamp - $first_stamp)      #当天的时间戳减去指定的时间戳
let  day=($day_stamp /86400 )                     #相差的时间戳除以一天的秒数就得到天数
echo  $day