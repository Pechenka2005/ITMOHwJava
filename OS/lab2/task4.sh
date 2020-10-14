#!/bin/bash
data=$(ps -A -o pid | tail -n +2)
for tempPid in $data
do
if [ -f "/proc/$tempPid/sched" ] && [ -f "/proc/$tempPid/status" ]
then
	ansPPid=$(grep '^PPid' /proc/$tempPid/status | awk '{print $2}')
	count=$(grep '^se.sum_exec_runtime' /proc/$tempPid/sched | awk '{print $3}' | awk -F. '{print $1}')
	allTime=$(grep '^nr_switches' /proc/$tempPid/sched | awk '{print $3}')
	ansART=($allTime / $count)
	echo "ProcessID=$tempPid : Parent_ProcessID=$ansPPid : Average_Running_Time=$ansART"
fi
done | sort -t= -k3 -n > file4.txt



