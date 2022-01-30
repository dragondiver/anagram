#!/bin/bash

root_folder=$(cd $(dirname $0); cd ..; pwd)
echo $root_folder

exec 3>&1


function _out() {
    echo "$(date +'%F %H:%M:%S') $@"
}

function run() {
    cd ${root_folder}
    _out "start"
    java -Duser.language=en -cp exploration/target/exploration-0.0.1-SNAPSHOT.jar $@
    _out "finished"
}

run $@