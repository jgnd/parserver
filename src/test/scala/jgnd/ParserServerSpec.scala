package jgnd

import RequestProtocol._
import ResponseProtocol._
import akka.actor.{Actor, Props}
import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http._
import spray.json._
import StatusCodes._
import spray.httpx.SprayJsonSupport._

class ParserServiceSpec extends Specification with Specs2RouteTest with ParserService {
  def actorRefFactory = system

  "RouterService" should {

    "return a result for POST requests with correct parameters" in {
      Post("/parse", Request("0","テストした")) ~> routes ~> check {
        status === OK
        responseAs[Response] === Response(List(
          Token("テスト", "テスト", "テスト", List("名詞", "サ変接続")),
          Token("し", "する", "シ", List("動詞", "自立")),
          Token("た", "た", "タ", List("助動詞")))
        )
      }
    }

    // this should be updated to ensure correct response is given
    "return a BadRequest error when text is over 10000 characters" in {
      Post("/parse",
        HttpEntity(MediaTypes.`application/json`, """{"text":"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis posuere at metus id consectetur. Nulla viverra arcu suscipit, lobortis ipsum sit amet, dignissim odio. Praesent pretium erat eu diam dapibus vehicula. Aenean nulla leo, placerat et ante lobortis, iaculis aliquet justo. Sed sit amet consequat est. Sed mattis eleifend finibus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc eleifend urna lobortis mauris bibendum porttitor. Integer velit purus, elementum id finibus quis, imperdiet pellentesque nisl. Integer vitae mollis nunc. Integer pellentesque faucibus dui eu lobortis. Etiam odio ipsum, blandit eu libero eu, volutpat posuere est. Vivamus accumsan lectus sed dictum ullamcorper. Phasellus odio turpis, bibendum nec condimentum quis, varius eleifend magna. Maecenas luctus venenatis ligula ac maximus. Aliquam molestie est et accumsan facilisis. Mauris volutpat egestas velit, at convallis lorem euismod vel. Etiam sit amet velit id arcu semper ullamcorper. Duis sed dignissim ipsum, in sodales felis. Duis dignissim ullamcorper ligula eget ultrices. Integer ullamcorper, nisl in hendrerit rutrum, velit nisi eleifend felis, et auctor libero nisi quis lectus. Quisque sollicitudin arcu sapien, lobortis luctus massa tincidunt tincidunt. Vivamus nulla tortor, imperdiet ac lacinia eu, facilisis in magna. Nullam sit amet diam vulputate eros consequat hendrerit eu non justo. Donec imperdiet mattis nisi non auctor. Curabitur mattis lacus ac arcu consequat egestas. Nulla facilisi. Interdum et malesuada fames ac ante ipsum primis in faucibus. Duis ac felis ac nunc egestas efficitur sed tincidunt elit. Nunc et mattis sapien, at congue tortor. Suspendisse ipsum ante, sagittis vitae erat ut, cursus semper lorem. Maecenas maximus viverra ornare. Sed pulvinar rhoncus nulla vitae finibus. Sed congue faucibus massa, eu semper elit scelerisque sit amet. Donec et rhoncus risus. Donec non nisi sit amet elit interdum cursus at eget erat. Maecenas venenatis vel est et mollis. Aliquam non bibendum diam, ut sollicitudin sapien. Fusce eu leo vulputate lectus blandit tincidunt. Sed lacus ante, gravida quis faucibus tincidunt, porta et mauris. Morbi at purus turpis. Nulla consectetur facilisis nibh quis luctus. Mauris aliquet, neque efficitur fringilla ultrices, massa ligula gravida justo, eget tempor felis arcu quis velit. Nunc tincidunt, erat sit amet elementum lacinia, lorem turpis volutpat orci, at ornare massa nibh vitae lectus. Sed efficitur semper justo ac finibus. Aliquam eu velit sollicitudin, egestas lacus quis, gravida mauris. Praesent nec quam mauris. Cras et libero sed lectus euismod rutrum non a velit. Nunc sed tincidunt mi. Interdum et malesuada fames ac ante ipsum primis in faucibus. Morbi eget eleifend felis, non imperdiet nunc. Maecenas neque ipsum, sodales dictum luctus id, cursus lacinia arcu. Etiam vitae metus neque. Suspendisse potenti. Sed vitae interdum mauris, vitae rhoncus turpis. Nunc erat nulla, aliquam id tempor eu, tincidunt varius enim. Suspendisse arcu elit, venenatis ac ipsum viverra, tempus pellentesque mauris. Nulla id sem et augue placerat rhoncus quis convallis lectus. Nulla augue lacus, pulvinar ac ligula ac, posuere maximus turpis. Maecenas convallis elit ligula, eu auctor neque sagittis vel. Nullam sem odio, scelerisque vitae facilisis quis, accumsan ut nibh. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis posuere at metus id consectetur. Nulla viverra arcu suscipit, lobortis ipsum sit amet, dignissim odio. Praesent pretium erat eu diam dapibus vehicula. Aenean nulla leo, placerat et ante lobortis, iaculis aliquet justo. Sed sit amet consequat est. Sed mattis eleifend finibus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc eleifend urna lobortis mauris bibendum porttitor. Integer velit purus, elementum id finibus quis, imperdiet pellentesque nisl. Integer vitae mollis nunc. Integer pellentesque faucibus dui eu lobortis. Etiam odio ipsum, blandit eu libero eu, volutpat posuere est. Vivamus accumsan lectus sed dictum ullamcorper. Phasellus odio turpis, bibendum nec condimentum quis, varius eleifend magna. Maecenas luctus venenatis ligula ac maximus. Aliquam molestie est et accumsan facilisis. Mauris volutpat egestas velit, at convallis lorem euismod vel. Etiam sit amet velit id arcu semper ullamcorper. Duis sed dignissim ipsum, in sodales felis. Duis dignissim ullamcorper ligula eget ultrices. Integer ullamcorper, nisl in hendrerit rutrum, velit nisi eleifend felis, et auctor libero nisi quis lectus. Quisque sollicitudin arcu sapien, lobortis luctus massa tincidunt tincidunt. Vivamus nulla tortor, imperdiet ac lacinia eu, facilisis in magna. Nullam sit amet diam vulputate eros consequat hendrerit eu non justo. Donec imperdiet mattis nisi non auctor. Curabitur mattis lacus ac arcu consequat egestas. Nulla facilisi. Interdum et malesuada fames ac ante ipsum primis in faucibus. Duis ac felis ac nunc egestas efficitur sed tincidunt elit. Nunc et mattis sapien, at congue tortor. Suspendisse ipsum ante, sagittis vitae erat ut, cursus semper lorem. Maecenas maximus viverra ornare. Sed pulvinar rhoncus nulla vitae finibus. Sed congue faucibus massa, eu semper elit scelerisque sit amet. Donec et rhoncus risus. Donec non nisi sit amet elit interdum cursus at eget erat. Maecenas venenatis vel est et mollis. Aliquam non bibendum diam, ut sollicitudin sapien. Fusce eu leo vulputate lectus blandit tincidunt. Sed lacus ante, gravida quis faucibus tincidunt, porta et mauris. Morbi at purus turpis. Nulla consectetur facilisis nibh quis luctus. Mauris aliquet, neque efficitur fringilla ultrices, massa ligula gravida justo, eget tempor felis arcu quis velit. Nunc tincidunt, erat sit amet elementum lacinia, lorem turpis volutpat orci, at ornare massa nibh vitae lectus. Sed efficitur semper justo ac finibus. Aliquam eu velit sollicitudin, egestas lacus quis, gravida mauris. Praesent nec quam mauris. Cras et libero sed lectus euismod rutrum non a velit. Nunc sed tincidunt mi. Interdum et malesuada fames ac ante ipsum primis in faucibus. Morbi eget eleifend felis, non imperdiet nunc. Maecenas neque ipsum, sodales dictum luctus id, cursus lacinia arcu. Etiam vitae metus neque. Suspendisse potenti. Sed vitae interdum mauris, vitae rhoncus turpis. Nunc erat nulla, aliquam id tempor eu, tincidunt varius enim. Suspendisse arcu elit, venenatis ac ipsum viverra, tempus pellentesque mauris. Nulla id sem et augue placerat rhoncus quis convallis lectus. Nulla augue lacus, pulvinar ac ligula ac, posuere maximus turpis. Maecenas convallis elit ligula, eu auctor neque sagittis vel. Nullam sem odio, scelerisque vitae facilisis quis, accumsan ut nibh. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis posuere at metus id consectetur. Nulla viverra arcu suscipit, lobortis ipsum sit amet, dignissim odio. Praesent pretium erat eu diam dapibus vehicula. Aenean nulla leo, placerat et ante lobortis, iaculis aliquet justo. Sed sit amet consequat est. Sed mattis eleifend finibus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc eleifend urna lobortis mauris bibendum porttitor. Integer velit purus, elementum id finibus quis, imperdiet pellentesque nisl. Integer vitae mollis nunc. Integer pellentesque faucibus dui eu lobortis. Etiam odio ipsum, blandit eu libero eu, volutpat posuere est. Vivamus accumsan lectus sed dictum ullamcorper. Phasellus odio turpis, bibendum nec condimentum quis, varius eleifend magna. Maecenas luctus venenatis ligula ac maximus. Aliquam molestie est et accumsan facilisis. Mauris volutpat egestas velit, at convallis lorem euismod vel. Etiam sit amet velit id arcu semper ullamcorper. Duis sed dignissim ipsum, in sodales felis. Duis dignissim ullamcorper ligula eget ultrices. Integer ullamcorper, nisl in hendrerit rutrum, velit nisi eleifend felis, et auctor libero nisi quis lectus. Quisque sollicitudin arcu sapien, lobortis luctus massa tincidunt tincidunt. Vivamus nulla tortor, imperdiet ac lacinia eu, facilisis in magna. Nullam sit amet diam vulputate eros consequat hendrerit eu non justo. Donec imperdiet mattis nisi non auctor. Curabitur mattis lacus ac arcu consequat egestas. Nulla facilisi. Interdum et malesuada fames ac ante ipsum primis in faucibus. Duis ac felis ac nunc egestas efficitur sed tincidunt elit. Nunc et mattis sapien, at congue tortor. Suspendisse ipsum ante, sagittis vitae erat ut, cursus semper lorem. Maecenas maximus viverra ornare. Sed pulvinar rhoncus nulla vitae finibus. Sed congue faucibus massa, eu semper elit scelerisque sit amet. Donec et rhoncus risus. Donec non nisi sit amet elit interdum cursus at eget erat. Maecenas venenatis vel est et mollis. Aliquam non bibendum diam, ut sollicitudin sapien. Fusce eu leo vulputate lectus blandit tincidunt. Sed lacus ante, gravida quis faucibus tincidunt, porta et mauris. Morbi at purus turpis. Nulla consectetur facilisis nibh quis luctus. Mauris aliquet, neque efficitur fringilla ultrices, massa ligula gravida justo, eget tempor felis arcu quis velit. Nunc tincidunt, erat sit amet elementum lacinia, lorem turpis volutpat orci, at ornare massa nibh vitae lectus. Sed efficitur semper justo ac finibus. Aliquam eu velit sollicitudin, egestas lacus quis, gravida mauris. Praesent nec quam mauris. Cras et libero sed lectus euismod rutrum non a velit. Nunc sed tincidunt mi. Interdum et malesuada fames ac ante ipsum primis in faucibus. Morbi eget eleifend felis, non imperdiet nunc. Maecenas neque ipsum, sodales dictum luctus id, cursus lacinia arcu. Etiam vitae metus neque. Suspendisse potenti. Sed vitae interdum mauris, vitae rhoncus turpis. Nunc erat nulla, aliquam id tempor eu, tincidunt varius enim. Suspendisse arcu elit, venenatis ac ipsum viverra, tempus pellentesque mauris. Nulla id sem et augue placerat rhoncus quis convallis lectus. Nulla augue lacus, pulvinar ac ligula ac, posuere maximus turpis. Maecenas convallis elit ligula, eu auctor neque sagittis vel. Nullam sem odio, scelerisque vitae facilisis quis, accumsan ut nibh."}""")
      ) ~> routes ~> check {
        handled must beFalse

        // status === BadRequest
        // responseAs[String] === "text too large"
      }
    }

    "leave GET requests to other paths unhandled" in {
      Get("/kermit") ~> routes ~> check {
        handled must beFalse
      }
    }

    "leave POST requests to other paths unhandled" in {
      Post("/kermit") ~> routes ~> check {
        handled must beFalse
      }
    }

    "return a MethodNotAllowed error for PUT requests to the parse path" in {
      Put("/parse") ~> sealRoute(routes) ~> check {
        status === MethodNotAllowed
        responseAs[String] === "HTTP method not allowed, supported methods: POST"
      }
    }

    "return a MethodNotAllowed error for GET requests to the parse path" in {
      Get("/parse") ~> sealRoute(routes) ~> check {
        status === MethodNotAllowed
        responseAs[String] === "HTTP method not allowed, supported methods: POST"
      }
    }
  }
}
