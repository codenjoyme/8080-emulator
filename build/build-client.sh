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

eval_echo "cd .."
eval_echo "ROOT=$(pwd)"
eval_echo "OUT=$ROOT/build/out"
eval_echo "BUILD=$ROOT/target/webapp-synth"
eval_echo "WEBAPP=$ROOT/src/main/webapp"
eval_echo "PLATFORM=lik"

color "Please get content from '$OUT' folder after build"

eval_echo "rm -rf $OUT"

eval_echo "$ROOT/mvnw clean package -DskipTests=true -Pjar-with-dependencies"

eval_echo "mkdir -p $OUT"
eval_echo "mv $BUILD/* $OUT"
eval_echo "cp -r $WEBAPP/run.* $OUT"

eval_echo "cd $OUT"
eval_echo "bash run.sh ./ $PLATFORM"

echo
color "Press Enter to continue"
read