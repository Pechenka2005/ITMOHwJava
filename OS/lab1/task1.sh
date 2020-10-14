#!/bin/bash
a=$1
b=$2
c=$3
if [[ $a -gt $b ]]; then b=$a; fi
if [[ $b -gt $c ]]; then c=$b; fi
echo $c

