#!/bin/bash
man bash | grep -E -o "[[:alnum:]]{4,}" | sort | uniq -c | sort -n -r -k1 | head -n3 | awk '{print $2}'
