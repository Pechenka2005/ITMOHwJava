#!/bin/bash
input="/etc"
grep -Eorhw --binary-file=text '[[:alnum:]_]+@[[:alnum:]]+\.[[:alnum:]]{2,3}' $input > emails.lst 
cat emails.lst

