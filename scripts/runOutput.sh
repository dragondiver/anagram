
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
_out "WriteToBufferedWriter"
./scripts/run.sh com.fhuber.schwarz.exploration.output.WriteToBufferedWriter testdata/englishwords.txt
_out "WriteToBufferedWriter"
./scripts/run.sh com.fhuber.schwarz.exploration.output.WriteToBufferedWriter testdata/manyenglishwords.txt
_out "WriteToFileWriter"
./scripts/run.sh com.fhuber.schwarz.exploration.output.WriteToFileWriter testdata/englishwords.txt
_out "WriteToFileWriter"
./scripts/run.sh com.fhuber.schwarz.exploration.output.WriteToFileWriter testdata/manyenglishwords.txt
_out "WriteToSystemOut"
./scripts/run.sh com.fhuber.schwarz.exploration.output.WriteToSystemOut testdata/englishwords.txt
_out "WriteToSystemOut"
./scripts/run.sh com.fhuber.schwarz.exploration.output.WriteToSystemOut testdata/manyenglishwords.txt
