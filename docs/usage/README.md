# Usage Tipps for the 3 modules

## General

### Prerequisites

This project is based on:
- jdk17
- git
- Apache Maven 3.8.4


To get the code please clone from Github: https://github.com/dragondiver/anagram.git

```sh
git clone https://github.com/dragondiver/anagram.git
```

### Installing

Change into folder *anagram* and build using maven

```sh
mvn clean package
```

### Setting up testdata

CChange into folder testdata. There is a file called setup.sh, ensure that you can execute it. This file uses the english dictionary, that is build into macos. 

If you are running another OS please check, if you can find this file somewhere in the internet and dowonload it. Adjust setup.sh afterwards.

After you executed setup.sh you should find 3 files in folder testdata:

```zsh
./setup.sh
rm: englishwords.txt: No such file or directory
rm: manyenglishwords.txt: No such file or directory
rm: awholelotofenglishwords.txt: No such file or directory
  235886 englishwords.txt
 11794300 manyenglishwords.txt
       7 sample.txt
       4 setup.sh
 12030197 total
 ```

| Name | Wordcount |
| :--- | ---: |
| sample.txt | 7 |
| englishwords.txt | 235886 |
| manyenglishwords.txt | 11794300 |

### Running the tests <a name = "tests"></a>

```bash
mvn clean verify
```

## Observer solution

Run Anagram with a testdatafile from anagram project root folder:

```sh
./scripts/runSolution.sh testdata/englishwords.txt
```

### :beetle: Troubleshooting <a name="trouble"></a>

You can check the logfiles in ./logs

## Request-Response solution

Run Anagram with a testdatafile from anagram project root folder:

```sh
./scripts/runAlternate.sh testdata/englishwords.txt
```

### :beetle: Troubleshooting <a name="trouble"></a>

You can check the logfiles in ./logs

## Quarkus solution

For the Quarkus solution i added only one testfile `sample.txt` to the src/main/resources folder.
You can add more, if you want.

You can start quarkus with 
```bash
java -jar quarkus/target/quarkus-app/quarkus-run.jar -Ddebug=7777 -Dquarkus.http.port=8080
````
Change the debug port, if you want to, or ommit it for speed.

For testing i recommend [httpie](https://httpie.io)

You can install it, for example with brew on macos

```bash
brew install httpie
```

Send a GET Message with the filename as Path Parameter

```bash
http GET localhost:8080/anagram/sample.txt
```




