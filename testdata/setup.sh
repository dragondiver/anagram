rm englishwords.txt manyenglishwords.txt
cat /usr/share/dict/words >englishwords.txt
for i in {1..50};do cat englishwords.txt >> manyenglishwords.txt; done
wc -l *