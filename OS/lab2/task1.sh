#!/bin/bash
ps -ef | grep '^sj' | wc -l > file1.log
ps -ef | grep '^sj' | awk '{print $2 ":" $8}' >> file1.txt

