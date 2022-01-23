#!/usr/bin/env bash

BLUE=94
GRAY=89
YELLOW=93

color() {
    message=$1
    [[ "$2" == "" ]] && color=$YELLOW || color=$2
    echo "[${color}m${message}[0m"
}

eval_echo() {
    command=$1
    [[ "$2" == "" ]] && color=$BLUE || color=$2
    color "${command}" $color
    echo
    eval $command
}

eval_echo "ROOT=$(pwd)"
eval_echo "java -jar $ROOT/emulator-1.0.jar"

echo
color "Press Enter to continue"
read