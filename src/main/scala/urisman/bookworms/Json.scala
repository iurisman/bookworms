package urisman.bookworms

import io.circe._
import io.circe.parser._

import scala.util.Try
object Json {
  def withBodyAs[T,R](body: String)(mapper: T => R)(implicit decoder: Decoder[T]): Try[R] = {
    for {
      json <- parse(body)
      t <- json.as[T]
    } yield mapper(t)
  }.toTry
}
