package jgnd

import spray.json.DefaultJsonProtocol
import spray.json.CompactPrinter
import spray.httpx.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._

// incoming json request model
case class Request(
    source: String,
    text: String
  ) {
  require(text.length <= 10000, "text is too large")
}

object RequestProtocol extends DefaultJsonProtocol {
  implicit val requestFormat = jsonFormat2(Request)
}

case class Token(
  originalToken: String,
  baseForm: String,
  reading: String,
  partsOfSpeech: List[String]
) {
}

// outgoing json response model
case class Response(words: List[Token]) {
}

object ResponseProtocol extends DefaultJsonProtocol {
  // implicit val printer = CompactPrinter
  implicit val tokenFormat = jsonFormat4(Token)
  implicit val resultFormat = jsonFormat1(Response)
}
