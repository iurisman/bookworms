package urisman.bookworms

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.util.Timeout
import urisman.bookworms.api.{Books, Root}

import scala.concurrent.{ExecutionContext, Future}

//#import-json-formats
//#user-routes-class
class UserRoutes(implicit ec: ExecutionContext) {

  //implicit val ec: ExecutionContext = system.executionContext
  //#user-routes-class
  import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
  //#all-routes
  //#users-get-post
  //#users-get-delete
  val userRoutes: Route =
  concat(
    // GET / - Health page
    pathEndOrSingleSlash {
      complete(Root.get())
    },
    pathPrefix("books") {
      concat(
        //#users-get-delete
        pathEnd {
          concat(
            get {
              onSuccess(Books.get) {
                row => complete(row)
              }
            },
            //            post {
            //              entity(as[Book]) { user =>
            //                onSuccess(createUser(user)) { performed =>
            //                  complete((StatusCodes.Created, performed))
            //                }
            //              }
            //            }
          )
        }
      )
    }
  )
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
