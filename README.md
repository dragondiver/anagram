# Anagram Detection

<div align="center">

[![Status](https://img.shields.io/badge/status-active-success.svg)]()
[![GitHub Issues](https://img.shields.io/github/issues/dragondiver/anagram.svg)](https://github.com/dragondiver/anagram/issues)
[![GitHub Pull Requests](https://img.shields.io/github/issues-pr/dragondiver/anagram.svg)](https://github.com/dragondiver/anagram/pulls)
[![License](https://img.shields.io/badge/license-Apache%202-blue.svg)]()

</div>

---

<p align="center"> List all anagrams of words contained within a file
    <br> 
</p>

## 📝 Table of Contents

- [About](#about)
- [Github Pages](#github_pages)
- [Getting Started](#getting_started)
- [Testdata](#testdata)
- [Running Tests](#test)
- [Usage](#usage)
- [Troubleshooting](#trouble)
- [Built Using](#built_using)
- [Authors](#authors)

## Additional Documentation

- [More about Design - tbd](https://dragondiver.github.io/anagram/design/)
- [More about research](https://dragondiver.github.io/anagram/exploration/)

## 🧐 About <a name = "about"></a>

Two words are defined as anagrams if they do share the same letters, but are in a different order (i.e. the English words race and care are anagrams).<br>
Given an input file which contains one word per line, as an output construct a list of all anagrams from that input file. Print those words to the console, where all words that are an anagram should each other should be on the same line.

**For ease of coding i reduced the language of words to english**

***This project contains 2 solutions now and 1 additional implementation example***

I reimplemented parts of the solution found in the module solution in a new module **requestresponse**.

I felt that was necessary because, during writing the tests i discovered that the initial solution was to complicated, and did not even fulfill my requirements on a good service.

But as you can see, i only change the App with the main class, and the controlling service. I also had to change the interface here, so i could not use an `alternative`.

I used the initial module `solution` as a dependency on the new module `requestresponse`.
As i was using a new `weld se` implementation, the namespace was already migrated to *Jakarta*.

But in the Quarkus example, i sadly had to copy all classes into the module, as quarkus does not have `Jakarta`namespace changes ready for now (or i was not apble to find it).

## Github Pages <a name = "github_pages"></a>

More Details on exploration, decision and implementation:
[Github](https://dragondiver.github.io/anagram/) - Github Pages

## 🏁 Getting Started <a name = "getting_started"></a>

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

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

## 🔧 Setting up testdata

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

## 🔧 Running the tests <a name = "tests"></a>

```bash
mvn clean verify
```

## 🎈 Usage <a name="usage"></a>

Run Anagram with a testdatafile from anagram project root folder:

```sh
./scripts/runSolution.sh testdata/englishwords.txt
```

## :beetle: Troubleshooting <a name="trouble"></a>

You can check the logfiles in ./logs

## ⛏️ Built Using <a name = "built_using"></a>

- [Weld SE](https://docs.jboss.org/weld/reference/latest/en-US/html/environments.html#weld-se) - CDI Implementation for Java SE
- [VueJs](https://vuejs.org/) - Web Framework for Documentation

## ✍️ Authors <a name = "authors"></a>

- [@FlorianHUber](https://github.com/dragondiver) 

