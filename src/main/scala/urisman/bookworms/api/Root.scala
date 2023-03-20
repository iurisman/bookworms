package urisman.bookworms.api

import akka.http.scaladsl.model._

object Root {
  def get(): HttpResponse = {
    HttpResponse(
      StatusCodes.OK,
      entity = HttpEntity(ContentTypes.`application/json`, """{"status":"OK"}"""))
  }
}
