
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
_out "HashKeyFromPrime"
./scripts/run.sh com.fhuber.schwarz.hashkey.HashKeyFromPrime testdata/englishwords.txt
_out "HashKeyFromPrime"
./scripts/run.sh com.fhuber.schwarz.hashkey.HashKeyFromPrime testdata/manyenglishwords.txt
_out "HashKeyFromSort"
./scripts/run.sh com.fhuber.schwarz.hashkey.HashKeyFromSort testdata/englishwords.txt
_out "HashKeyFromSort"
./scripts/run.sh com.fhuber.schwarz.hashkey.HashKeyFromSort testdata/manyenglishwords.txt
