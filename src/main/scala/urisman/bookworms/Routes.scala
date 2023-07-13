package urisman.bookworms

import akka.http.scaladsl.model.{HttpResponse}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ExceptionHandler, Route}
import com.typesafe.scalalogging.LazyLogging
import urisman.bookworms.api.{Books, Copies, Root}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success => TrySuccess}

class Routes(implicit ec: ExecutionContext) {

  import Routes._

  private val rootRoutes = pathEndOrSingleSlash {
    get {
      // GET / - Health page
      // complete(HttpResponse(StatusCodes.OK))
      complete(Root.get())
    }
  }

  private val booksRoutes = pathPrefix("books") {
    concat(
      pathEnd {
        concat(
          get {
            onSuccess(Books.get)(resp => complete(resp))
          },
          // This would be a place to implement adding a book
          post {
            entity(as[String]) {
              body =>
                onSuccess(withBodyAs[Book](body)(Books.post))(resp => complete(resp))
            }
          }
        )
      },
      path(Segment) { bookId =>
        get {
          onSuccess(Books.get(bookId.toInt))(resp => complete(resp))
        }
      }
    )
  }

  private val copiesRoutes = pathPrefix("copies") {
    concat(
      pathEndOrSingleSlash {
        put {
          entity(as[String]) {
            body =>
              onSuccess(withBodyAs[Copy](body)(Copies.update))(resp => complete(resp))
          }
        }
      },
      path(Segment) { copyId =>
        put {
          onSuccess(Copies.hold(copyId.toInt))(resp => complete(resp))
        }
      }
    )
  }

  val routes: Route = {
    handleExceptions(customExceptionHandler) {
      rootRoutes ~ booksRoutes ~ copiesRoutes
    }
  }
}

object Routes extends LazyLogging {

  import akka.http.scaladsl.model.StatusCodes._
  import io.circe._

  private val customExceptionHandler = ExceptionHandler {
    case t: Throwable =>
      logger.error("Unhandled exception:", t)
      complete(HttpResponse(InternalServerError, entity = "Something is rotten in the State of Denmark"))
  }

  private def withBodyAs[T](body: String)(toResponse: T => Future[HttpResponse])
                           (implicit decoder: Decoder[T]): Future[HttpResponse] = {

    parseAndThen(body)(toResponse) match {
      case Failure(t) =>
        Future.successful(
          HttpResponse(
            BadRequest,
            entity = s"Exception while parsing JSON in request: ${t.getMessage}")
        )
      case TrySuccess(respF) => respF
    }
  }
}