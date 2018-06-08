#! /bin/bash
DIRECTORY_SOURCE="/home/rameshv/IdeaProjects/CommandLine/src/test"
FILE_SOURCE="/home/rameshv/IdeaProjects/CommandLine/src/input.txt"
TEXT_SOURCE="abc"

CLASS_FILE="CommandLine"
EXEC="java $CLASS_FILE"
echo "------JavaMatcher--------"
com=" $EXEC '-j' '-p' 'abc*a+c*b.' '-d' $DIRECTORY_SOURCE"
eval ${com}
echo "------CustomMatcher---------"
com=" $EXEC '-c' '-p' 'abc*a+c*b.' '-d' $DIRECTORY_SOURCE"
eval ${com}