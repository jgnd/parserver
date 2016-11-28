package jgnd

import RequestProtocol._
import ResponseProtocol._
import scala.concurrent.duration._
import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._
import scala.collection.mutable.MutableList
import scala.collection.JavaConversions._
import spray.httpx.SprayJsonSupport._
import com.atilika.kuromoji.ipadic.neologd.Tokenizer

class ParserServer extends Actor with ParserService {

  def actorRefFactory = context

  def receive = runRoute(routes)
}

trait ParserService extends HttpService {

  implicit def rejectionHandler = RejectionHandler {
    case MalformedRequestContentRejection(errorMessage, _) :: _ =>
      complete(StatusCodes.BadRequest, errorMessage)
  }

  val routes =
    post {
      pathPrefix("parse") {
        entity(as[Request]) { request =>
          complete(parse(request))
        }
      }
    }

  def parse(request: Request): Response = {
      val tokenizer = new Tokenizer

      var tokens = new MutableList[Token] // ArrayList[String]

      var begin = 0
      for ( token <- tokenizer.tokenize(request.text) ) {
        var partsOfSpeech = List(
          token.getPartOfSpeechLevel1,
          token.getPartOfSpeechLevel2,
          token.getPartOfSpeechLevel3,
          token.getPartOfSpeechLevel4
        ).filter(_ != "*")
        tokens += new Token(
                        token.getSurface,
                        token.getBaseForm,
                        token.getReading,
                        partsOfSpeech)
      }
      return Response(tokens.toList)
    }
}
