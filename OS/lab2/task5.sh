#!/bin/bash
input="file4.txt"
output="file5.txt"
predPPid=""
n=0
a=0
while read line
do
ppid=$(echo $line | awk '{print $3}' | awk -F= '{print $2}')
aver=$(echo $line | awk '{print $5}' | awk -F= '{print $2}')
if [[ -z $predPPid ]]
then
	predPPid=$ppid
fi
if [[ $ppid == $predPPid ]]
then
	echo $line
	((n++))
	((a += aver))
else
	((a /= n))
	echo "Average_Sleeping_Children_of_ParentID=$predPPid is $a"
	predPPid=$ppid
	n=1
	a=$aver
	echo $line
fi
done < $input > $output

((a /= n))
echo "Average_Sleeping_Children_of_ParentID=$predPPid is $a" >> $output
