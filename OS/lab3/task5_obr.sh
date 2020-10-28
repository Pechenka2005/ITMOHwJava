#!/bin/bash
isNum="^[0-9]+"
ans=1
mode="+"
(tail -f pipe) |
while true; do
	read line
	case "$line" in
	"QUIT")
		echo "ans = $ans"
		killall tail
		exit
	;;
	"+")
		mode="+"
		echo "mode: addition"
	;;
	"*")
		mode="*"
		echo "mode: multiplication"
	;;
	*)
		if [[ $line =~ $isNum ]]
		then
			case "$mode" in
			"+")
				ans=$(("$ans" + "$line"))
			;;
			"*")
				ans=$(("$ans" * "$line"))
			;;
			esac
		else
			echo "Error in $line"
		fi
	;;
	esac
done


