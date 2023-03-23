package urisman.bookworms.api

import akka.http.scaladsl.model._
import io.circe.Encoder
import io.circe.syntax._

trait Endpoint {

  def okResponse[T](body: T)(implicit encoder: Encoder[T]): HttpResponse =
    HttpResponse(
      StatusCodes.OK,
      entity = HttpEntity(
        ContentTypes.`application/json`,
        body.asJson.toString())
  )
}
