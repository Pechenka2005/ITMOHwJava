#!/bin/bash
pid=$(cat temp6.pid)
while true; do
	read line
	case $line in
		"+")
			kill -USR1 $pid
		;;
		"*")
			kill -USR2 $pid
		;;
		"TERM")
			kill -SIGTERM $pid
		;;
		*)
			:
		;;
	esac
done
