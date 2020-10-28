#!/bin/bash
cpulimit --limit 10 ./task4_generator.sh &
cpulimit --limit 30 ./task4_generator.sh &
./task4_generator.sh &
kill $!
