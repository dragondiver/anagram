# Design considerations

## First solution

For the first solution i was thinking: `what kind of application would need this anagram service?`
and came up with the idea to have an observer that will wait for an event, sent for example from a FileWatcher,
then at a controller/service which would read in the file word for word, and send the words to a service, which would collect the words into a Map or Database (for very large files).

Therefore the words will be stored into a Anagram Containers, each container containing only anagrams of the first word. To identify the correct container, words will provide a key. The easiest method would be to sort the characters of the words. According to my tests (see [Explorations](/exploration/)) the performance is also competitive.

Using this key, the container can be stored in a Hashmap, or for larger files, into a database.

Once this is finished, the controller/service will fire an event, that the AnagramMap is ready for Output.
The observer will call the AnagramOutputService, which will print the Anagrams to System.out, thereby ommitting all the AnagramContainers, which have only one word, and therefor do not contain Anagrams.

I used a CDI Container to be able to have dependency injection. The Implementation is the JBOSS WELD SE. Using Alternatives one could choose different implementations for different strategies, eg. DB Storage, or output into Files etc.

## Second solution `requestresponse``

While writing the UnitTests i discovered, that the first solution has to much complexity and could be implemented easy as a Request-Response Service. As i already delivered the first