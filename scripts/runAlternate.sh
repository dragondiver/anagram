#!/bin/bash

root_folder=$(cd $(dirname $0); cd ..; pwd)

exec 3>&1


function _out() {
    echo "$(date +'%F %H:%M:%S') $@"
}

function run() {
    cd ${root_folder}
    # _out "start"
    java -Duser.language=en -Djava.util.logging.config.file=${root_folder}/logs/logging.properties -jar requestresponse/target/Anagram/Anagram.jar  $@
    # _out "finished"
}

run $@