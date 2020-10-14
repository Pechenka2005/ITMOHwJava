#!/bin/bash
ps -ef | awk '$8 ~ /^\/sbin\// {print $2}' > file2.log
