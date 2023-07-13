package urisman.bookworms

import akka.actor.testkit.typed.scaladsl.ActorTestKit
import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import io.circe.Decoder
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import scala.util.{Try, Success, Failure}
class RouterTest extends AnyWordSpec with Matchers with ScalaFutures with ScalatestRouteTest {

  // Akka HTTP route testkit does not yet support a typed actor system (https://github.com/akka/akka-http/issues/2036)
  override def createActorSystem(): akka.actor.ActorSystem = ActorTestKit().system.classicSystem

  // Our API router
  private lazy val routes = new Routes().routes

  private def withBodyAs[T](body: String)(bloc: T => Unit)
                           (implicit decoder: Decoder[T]): Unit = {
    Json.withBodyAs(body)(bloc) match {
      case Failure(t) =>
        fail("Unexpected exception in test", t)
      case Success(_) => // AOK!
    }
  }


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
        println(entityAs[String])
      }
    }
  }

}