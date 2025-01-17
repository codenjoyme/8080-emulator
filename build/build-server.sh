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
    echo
}

eval_echo "HOST=localhost"
eval_echo "PORT=8080"

eval_echo "cd .."
eval_echo "ROOT=$(pwd)"

color "Please run 'http://$HOST:$PORT/' after build"

eval_echo "$ROOT/mvnw clean jetty:run -DskipTests=true -Dserver.host=$HOST -Dserver.port=$PORT -Prun-server"

echo
color "Press Enter to continue"
read