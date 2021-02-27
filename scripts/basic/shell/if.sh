#!/bin/bash
if [ $# -le 0 ]; then
    echo "no parameters" && exit 0
fi
if [ $1 -eq "1" ];then
  echo "zhen lihai"
elif [ $1 -eq "2" ]; then
    echo "zhen niubi"
else
  echo "no problem"
fi