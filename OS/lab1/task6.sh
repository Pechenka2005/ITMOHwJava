#!/bin/bash
input="/var/log/syslog"
grep "warning:" $input | sed 's/warning:/Warning:/' > full.log
grep "<info>" $input | sed 's/<info>/Information:/' >> full.log
cat full.log

