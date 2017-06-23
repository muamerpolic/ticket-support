#!/bin/bash

file=*.tmp

if [ -f $file ] ; then
    rm $file
fi
