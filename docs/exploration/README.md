---
sidebarDepth: 2
---
# Finding the Best Solution

## The Problem
Given an input file which contains one word per line, as an output construct a list of all anagrams from that input file. Print those words to the console, where all words that are an anagram should each other should be on the same line.

## Main Topics in Approach

#### Where to store the anagrams collection

As long as we do have enough memory, the obvious solution is a map, with larger amounts of words, an appropriate solution would be a database of some kind, that is able to handle the amount of data. 
#### How to identify an anagram?

Two words are defined as anagrams if they do share the same letters, but are in a different order (i.e. the English words race and care are anagrams).
Immediate solutions would be to sort the characters and use the string of sorted char as key.

To be able to identify a colection of anagrams, we need a key. But because sorting is expensive, an other solution could be to calculate the hashcode of a word by calculating the product of each character mapping prime. This works since the product of 2 primes is unique from the products of any other primes. I tested the performance in the exploration sub project. 


#### Is writing to System.out affecting my performance?

Another problem might occur in the output speed.

## Testing the performane of output

In the **exploration** module in the package *com.fhuber.schwarz.output** i compare writing with System.out and with a BufferedWriter.
You can run it from root folder with ./scripts/runOutput.sh. In Folder results you can find the results.
| Method | Time | Number of Words |
|---|---:|---:|
|System.out.println()|536309458 ms | 235886
|System.out.println()|42281179833 ms | 11794300
|BufferedWriter      |288006959 ms | 235886
|BufferedWriter      |18766103417 ms | 11794300
|FileWriter |395819250 ms | 235886
|FileWriter |8038425791 ms | 11794300

You can see, it is worthwhile to think about that too. For large files the advantage of BufferedWriter seems to shrink.
It is also important to consider the environment, e.g. whether the terminal displays the data, or it is logged into file.
To reduce side effects you might want to use
```bash
./scripts/runOutput.sh >/dev/null
```
For the moment i will stick with writing to FileWriter directly, because i do have a fast Apple Macbook Air M1 ;-)

## Measuring the performance of the hashKey creation

To make sure to find the anagrams, i added code to extract special characters and blanks from the lines.
```java
word.toLowerCase().replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}]", "")
```
This code was not examined for performance to reduce the problem.

In the **exploration** module in the package *com.fhuber.schwarz.hashkey** i compare generating with sorted char array against *prime_mod*.
You can run it from root folder with ./scripts/runhash.sh. In Folder results you can find the results.
```bash
./scripts/runHash.sh >/dev/null
```

| Method | Time | Number of Words |
|---|---:|---:|
|HashKeyFromPrime|420534792 ms | 235886
|HashKeyFromPrime|8186032875 ms | 11794300
|HashKeyFromSort      |408632417 ms | 235886
|HashKeyFromSort      |8063013458 ms | 11794300

I will choose the method **HashKeyFromSort** in implementation

## Testing the perfomance of the Map

In the **exploration** module in the package *com.fhuber.schwarz.map** i test the performance against HashMap and ConcurrentHashMap
You can run it from root folder with ./scripts/runMap.sh. In Folder results you can find the results.
```bash
./scripts/runMap.sh >/dev/null
```
HashMap is not threadsafe, so it cannot execute in parallel. But as reading from File should not easily be parallelized it will do.
Still the performance of ConcurrentHashMap is comparable, so we might just stick with it as well.
Using an in Memory HashMap stops loading at 57000000 words. So this is the place, where we should start working with a DB.


