#! /bin/bash
DIRECTORY_SOURCE="/home/rameshv/IdeaProjects/JavaGrep/src/test"
FILE_SOURCE="/home/rameshv/IdeaProjects/JavaGrep/src/input.txt"
TEXT_SOURCE="abc"

CLASS_FILE="JavaGrep"
EXEC="java $CLASS_FILE"
echo "------JavaMatcher--------"
com=" $EXEC '-j' '-p' 'abc*a+c*b.' '-d' $DIRECTORY_SOURCE"
eval ${com}
echo "------CustomMatcher---------"
com=" $EXEC '-c' '-p' 'abc*a+c*b.' '-d' $DIRECTORY_SOURCE"
eval ${com}