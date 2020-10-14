#!/bin/bash
data=$(ps -e --format="pid cmd" | tail -n +2)
IFS=$'\n'
for line in $data
do
pid=$(echo $line| awk '{print $1}')
if [ -f /proc/$pid/io ]
then
	echo $line | awk 'BEGIN{ORS=""}{$1 = $1"*"; print $0}'
	cat /proc/$pid/io | awk '{if ($1 == "read_bytes:") print "*"$2} '
fi
done > file8.txt

#sleep 1m

data2=$(cat file8.txt)
for line in $data2 
do
pid=$(echo $line | awk 'BEGIN{FS="*"}{print $1}')
new=$(cat /proc/$pid/io | awk '{if ($1 == "read_bytes:") print $2}')
old=$(echo $line | awk 'BEGIN{FS="*"}{print $3}')
echo $line | awk -v nw=$new,od=$old 'BEGIN{FS="*"; OFS="*"}{$3 = nw - od; print $0}'
done | sort -t* -n -r -k3 | head -n 3 | tr '*' ':' 
rm file8.txt
