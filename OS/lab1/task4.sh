#!/bin/bash
if [[ "$HOME" == "$PWD" ]]
then
	echo "Путь к домашнему директорию :"
	echo $HOME
	exit 0
else
	echo "Ошибка, текущая и домашняя директория не совпадают"
	exit 1
fi

