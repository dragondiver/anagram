# Finding the Best Solution

## The Problem
Given an input file which contains one word per line, as an output construct a list of all anagrams from that input file. Print those words to the console, where all words that are an anagram should each other should be on the same line.

### Sharpening the problem

To make sure to find the anagrams, i added code to extract special characters and blanks from the lines.
```java
word.toLowerCase().replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}]", "")
```
This code was not examined for performance to reduce the problem.
