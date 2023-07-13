package urisman.bookworms

import akka.http.scaladsl.model._
class RouterTest extends RouterSpec {

  "Root route" should {
    "return health page with GET" in {
      val request = HttpRequest(uri = "/")
      request ~> routes ~> check {
        status shouldBe StatusCodes.OK
        contentType shouldBe ContentTypes.`application/json`
        entityAs[String] shouldBe """{"status":"OK"}"""
      }
    }
  }

  "/books route" should {
    "return list of books with GET" in {
      val request = HttpRequest(uri = "/books")
      request ~> routes ~> check {
        status shouldBe StatusCodes.OK
        contentType shouldBe ContentTypes.`application/json`
        withBodyAs[Seq[Book]](body = entityAs[String]) {
          seq =>
            seq.size shouldBe 5
            seq.find(book => book.title == "Programming in Scala, Fifth Edition") shouldBe Symbol("defined")
        }
      }
    }
  }

}