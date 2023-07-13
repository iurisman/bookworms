package urisman.bookworms

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

object Main {

  private def startHttpServer(routes: Route)(implicit system: ActorSystem): Unit = {

    implicit val ec: ExecutionContext = system.dispatcher

    val futureBinding = Http().newServerAt("localhost", 8080).bind(routes)
    futureBinding.onComplete {
      case Success(binding) =>
        val address = binding.localAddress
        system.log.info("Server online at http://{}:{}/", address.getHostString, address.getPort)
      case Failure(ex) =>
        system.log.error("Failed to bind HTTP endpoint, terminating system", ex)
        system.terminate()
    }
  }

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("Bookworms-HTTP-Server")
    val routes = new Routes()(system.dispatcher)
    startHttpServer(routes.routes)(system)
  }
}
