package urisman.bookworms

import akka.actor.testkit.typed.scaladsl.ActorTestKit
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import io.circe.Decoder
import org.scalatest.OptionValues
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.util.{Failure, Success}

class RouterSpec extends AnyWordSpec with Matchers with ScalaFutures with OptionValues with ScalatestRouteTest {

  // Akka HTTP route testkit does not yet support a typed actor system (https://github.com/akka/akka-http/issues/2036)
  override def createActorSystem(): akka.actor.ActorSystem = ActorTestKit().system.classicSystem

  // Our API router
  protected lazy val routes: Route = new Routes().routes

  protected def withBodyAs[T](body: String)(bloc: T => Unit)
                           (implicit decoder: Decoder[T]): Unit = {
    parseAndThen(body)(bloc) match {
      case Failure(t) =>
        fail("Unexpected exception in test", t)
      case Success(_) => // AOK.
    }
  }

}