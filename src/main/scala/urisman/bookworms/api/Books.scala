package urisman.bookworms.api

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse, StatusCodes}
import urisman.bookworms._
import urisman.bookworms.db.BookwormsDatabase
import io.circe._
import io.circe.parser._
import io.circe.generic.semiauto._
import io.circe.syntax._

import scala.concurrent.{ExecutionContext, Future}

object Books {

  def get(implicit ec: ExecutionContext): Future[HttpResponse] = {
    BookwormsDatabase.getBooks
      .map {
        books =>
          val body = books.asJson
          HttpResponse(StatusCodes.OK, entity = HttpEntity(ContentTypes.`application/json`, body.toString()))
      }
  }
}
