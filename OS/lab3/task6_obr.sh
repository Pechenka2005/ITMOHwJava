#!/bin/bash
echo $$ > temp6.pid
ans=1
usr1()
{
	ans=$(($ans + 2))
}

usr2()
{
	ans=$(($ans * 2))
}

term()
{
	echo "exit. ans = $ans"
	exit
}

trap 'usr1' USR1
trap 'usr2' USR2
trap 'term' SIGTERM

while true; do
	sleep 1
done


