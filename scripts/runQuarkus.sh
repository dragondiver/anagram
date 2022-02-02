#!/bin/bash

root_folder=$(cd $(dirname $0); cd ..; pwd)

exec 3>&1


function _out() {
    echo "$(date +'%F %H:%M:%S') $@"
}

function run() {
    cd ${root_folder}
    # _out "start"
 java -jar quarkus/target/quarkus-app/quarkus-run.jar -Ddebug=7777
    # _out "finished"
}

_out "please install httpie (eg. brew install httpie)"
_out "and start a client with:  http GET localhost:8080/anagram/sample.txt"
_out "its the only working endpoint for now"
_out "Debug Port is open at 7777"

run $@