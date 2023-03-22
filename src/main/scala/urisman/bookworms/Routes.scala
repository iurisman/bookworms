package urisman.bookworms

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import ch.megard.akka.http.cors.scaladsl.CorsDirectives.cors
import urisman.bookworms.api.{Books, Root}

import scala.concurrent.ExecutionContext
class Routes(implicit ec: ExecutionContext) {

  val userRoutes: Route = {
    cors() {
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
                  onSuccess(Books.get) { body =>
                    complete(
                      HttpResponse(
                        StatusCodes.OK,
                        entity = HttpEntity(
                          ContentTypes.`application/json`,
                          body.toString())
                      )
                    )
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
