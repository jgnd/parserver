# parserver

A simple server to parse Japanese text, returning a list of tokens.

For example:

```
curl -H 'Content-Type:application/json' -X POST -d '{"source": "test", "text": "テストです"}' localhost:8080/parse
{
  "words": [{
    "originalToken": "テスト",
    "baseForm": "テスト",
    "reading": "テスト",
    "partsOfSpeech": ["名詞", "サ変接続"]
  }, {
    "originalToken": "です",
    "baseForm": "です",
    "reading": "デス",
    "partsOfSpeech": ["助動詞"]
  }]
}
```

## Getting started

### Requirements

* [sbt](http://www.scala-sbt.org/download.html)

Run `sbt` and `re-start` from the sbt commandline to build and run the server. Run tests with `test`.

Note: this uses [kuromoji](https://github.com/atilika/kuromoji) for parsing and dictionary JARs should be placed in the 'lib' directory.
