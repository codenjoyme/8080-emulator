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

eval_echo "HOST=127.0.0.1"
eval_echo "PORT=8081"

eval_echo "cd .."
eval_echo "ROOT=$(pwd)"

color "Please run 'http://localhost:8080/' after build"

eval_echo "$ROOT/mvnw clean jetty:run -DskipTests=true -Dserver.host=$HOST -Dserver.port=$PORT -Prun-server"

echo
color "Press Enter to continue"
read