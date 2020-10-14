#!/bin/bash
result=""
read a
while [[ "$a" != "q" ]]
do 
	result+=$a
	read a
done
echo $result

