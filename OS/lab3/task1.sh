#!/bin/bash
curDate=$(date '+%d.%m.%y_%H:%M:%S')
textSuccess="catalog test was created successfully"
mkdir ~/test && {
	echo textSuccess > ~/report
	touch ~/test/$curDate
}
ping www.new_nikogo.ru || echo $curDate "Error" >> ~/report


