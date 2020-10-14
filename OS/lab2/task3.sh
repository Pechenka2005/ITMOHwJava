#!/bin/bash
ps -ef | sed 1d | awk '{print $5 " " $2 }' | sort -n -r -k1 | head -n1 | awk '{print $2}'
