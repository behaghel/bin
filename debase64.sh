#!/bin/sh
echo $1 | openssl enc -a -d; echo "\n"
