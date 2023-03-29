package urisman.bookworms

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{Directive1, ExceptionHandler, Route}
import ch.megard.akka.http.cors.scaladsl.CorsDirectives.cors
import com.typesafe.scalalogging.LazyLogging
import urisman.bookworms.api.{Books, Copies, Root}

import scala.concurrent.{ExecutionContext, Future}

class Routes(implicit ec: ExecutionContext) {

  import Routes._

  private val rootRoutes = pathEndOrSingleSlash {
    get {
      // GET / - Health page
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
          //            post {
          //              entity(as[Book]) { user =>
          //                onSuccess(createUser(user)) { performed =>
          //                  complete((StatusCodes.Created, performed))
          //                }
          //              }
          //            }
        )
      },
      path(Segment) { bookId =>
        get {
          onSuccess(Books.get(bookId.toInt)) (resp => complete(resp))
        }
      }
    )
  }

  private val copiesRoutes = pathPrefix("copies") {
    concat(
      pathEndOrSingleSlash {
        put {
          put {
            entity(as[String]) {
              body =>
                onSuccess(withBodyAs[Copy](body)(Copies.update))(resp => complete(resp))
            }
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
    cors() {
      handleExceptions(customExceptionHandler) {
        rootRoutes ~ booksRoutes ~ copiesRoutes
      }
    }
  }
  //        //#users-get-delete
//        //#users-get-post
//        path(Segment) { name =>
//          concat(
//            get {
//              //#retrieve-user-info
//              rejectEmptyResponse {
//                onSuccess(getUser(name)) { response =>
//                  complete(response.maybeUser)
//                }
//              }
//              //#retrieve-user-info
//            },
//            delete {
//              //#users-delete-logic
//              onSuccess(deleteUser(name)) { performed =>
//                complete((StatusCodes.OK, performed))
//              }
//              //#users-delete-logic
//            })
//        })
//      //#users-get-delete
//    }
//  )
}

object Routes extends LazyLogging {

  import akka.http.scaladsl.model.StatusCodes._
  import scala.reflect.runtime.universe._
  import io.circe._
  import io.circe.parser._

  private val customExceptionHandler = ExceptionHandler {
    case t: Throwable =>
      logger.error("Unhandled exception:", t)
      complete(HttpResponse(InternalServerError, entity = "Something is rotten in the State of Denmark"))
  }

  private def withBodyAs[T](body: String)(f: T => Future[HttpResponse])
                             (implicit decoder: Decoder[T], tt: TypeTag[T]): Future[HttpResponse] = {

    parse(body) match {
      case Left(ex) =>
        Future.successful(
          HttpResponse(
            BadRequest,
            entity = s"Exception while parsing JSON in request: ${ex.getMessage()}")
        )
      case Right(json) =>
        json.as[T] match {
          case Left(ex) =>
            val msg = s"Unable to unmarshal request body to class ${tt.tpe.getClass.getName} "
            Future.successful(HttpResponse(BadRequest, entity = msg))
          case Right(t) =>
            f(t)
        }
    }
  }
}