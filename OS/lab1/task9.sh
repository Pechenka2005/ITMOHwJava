#!/bin/bash
input="/var/log"
find $input -name "*.log" | xargs wc -l



