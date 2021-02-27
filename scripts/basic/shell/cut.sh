#!/bin/bash
#cut -f 2,3 -d " " cut.txt
#cat cut.txt | grep "guan" | cut -d " " -f 2
ifconfig lo0 | grep "inet" | cut -d ":" -f 2 | cut -d " " -f 2