
#!/bin/bash

root_folder=$(cd $(dirname $0); cd ..; pwd)
echo $root_folder

rm -rf results;
mkdir results;

exec 3>&1

function _out() {
    echo "$(date +'%F %H:%M:%S') $@"
}
mvn clean install
_out "HashMapExploration"
./scripts/run.sh com.fhuber.schwarz.exploration.map.HashMapExploration testdata/englishwords.txt
_out "HashMapExploration"
./scripts/run.sh com.fhuber.schwarz.exploration.map.HashMapExploration testdata/manyenglishwords.txt
_out "ConcurrentMapExploration"
./scripts/run.sh com.fhuber.schwarz.exploration.map.ConcurrentMapExploration testdata/englishwords.txt
_out "ConcurrentMapExploration"
./scripts/run.sh com.fhuber.schwarz.exploration.map.ConcurrentMapExploration testdata/manyenglishwords.txt
