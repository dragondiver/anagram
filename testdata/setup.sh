rm englishwords.txt manyenglishwords.txt awholelotofenglishwords.txt
cat /usr/share/dict/words >englishwords.txt
for i in {1..50};do cat englishwords.txt >> manyenglishwords.txt; done
# for i in {1..500};do cat manyenglishwords.txt >> awholelotofenglishwords.txt; done
wc -l *