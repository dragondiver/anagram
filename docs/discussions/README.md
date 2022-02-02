# Discussions

## Maintainability

There are few dependencies, so to keep them updated should be easy.
In Github i configured Dependabot alerts, to get warnings on outdated or unsecure dependencies

The CodeQL actions in Github do checks for the following java versions:

```yaml
strategy:
    fail-fast: false
    matrix:
        java-version: [ 8.0.192, 8, 11.0.3, 17, 18-ea ]
```

The reduction to just a few classes should ensure maintainability

## Scalability

The current implementation was testet with 11.794.300 words without problems on a MacBook Air M1 / 8GB Ram
For bigger files one would use a database, so we do not have to keep the Concurrent HashMap in memory.
Implementation of AnagramStorage would have to change, or you could at another CDI Alternative and inject it using either:
- @Named
- @Produces
- Custom Qualifiers

If you would like to scale in number of processes, one would need a UUID to identify the AnagramContiners in the DB

## Performance

As pointed out in [Exploration](/exploration/) i did a day of exploration into performance considerations.
Obviously, processor and more memory would increase performance to a point.
If you gad large files, you could also think about splitting the tasks up into portions, for example partitioning the data by word length. All Anagrams definitely should have the same word length. And then do some parallel processing.