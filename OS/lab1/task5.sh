#!/bin/bash
input="/var/log/syslog"
awk '$6=="<info>" {print $0}' $input > info.log

