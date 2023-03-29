package urisman.bookworms

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ExceptionHandler, Route}
import ch.megard.akka.http.cors.scaladsl.CorsDirectives.cors
import com.typesafe.scalalogging.LazyLogging
import urisman.bookworms.api.{Books, Copies, Root}

import scala.concurrent.ExecutionContext
class Routes(implicit ec: ExecutionContext) {

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
            onSuccess(Books.get) { resp => complete(resp) }
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
          onSuccess(Books.get(bookId.toInt)) (resp => complete (resp))
        }
      }
    )
  }

  private val copiesRoutes = pathPrefix("copies") {
    concat(
      path(Segment) { copyId =>
        put {
          onSuccess(Copies.hold(copyId.toInt))(resp => complete(resp))
        }
      }
    )
  }

  val routes: Route = {
    cors() {
      handleExceptions(Routes.customExceptionHandler) {
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
  private val customExceptionHandler = ExceptionHandler {
    case t: Throwable =>
      logger.error("Unhandled exception:", t)
      complete(HttpResponse(StatusCodes.InternalServerError, entity = "Something is rotten in the State of Denmark"))
  }
}