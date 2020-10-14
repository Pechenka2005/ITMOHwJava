#!/bin/bash
echo "Введите цифру от 1 до 4: "
echo "1) Запустить редоактор nano"
echo "2) Запусть редактор vi"
echo "3) Открыть браузер links"
echo "4) Выход"
read num
case $num in
1) 
	nano
	;;
2)
	vi
	;;
3)
	links
	;;
4) 
	exit 0
	;;
*) 
	echo "Вы ошиблись в вводе!!!"
esac

