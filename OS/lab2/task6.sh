#!/bin/bash
data=$(ps -e -o pid | tail -n +2)
for tempPid in $data
do
if [ -f "/proc/$tempPid/status" ]
then
	grep "^VmRSS" /proc/$tempPid/status | awk -v x=$tempPid '{print $2 " " x}'
fi
done | sort -n -k1 | tail -n 1 | awk '{print $2}'
top

