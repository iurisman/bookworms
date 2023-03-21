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

//          implicit lazy val e1: Encoder[Author] = deriveEncoder[Author]
//          implicit lazy val e4: Encoder[Seq[Author]] = deriveEncoder[Seq[Author]]
          import java.sql.Date
          implicit val e1: Encoder[Date] = new Encoder[Date] {
            override def apply(sqlDate: Date): Json =
              Encoder.encodeString(sqlDate.toString)
          }
          implicit val e2: Encoder[Book] = deriveEncoder[Book]
          implicit val e3: Encoder[List[Book]] = deriveEncoder[List[Book]]

          val body = books.asJson
          HttpResponse(StatusCodes.OK, entity = HttpEntity(ContentTypes.`application/json`, body.toString()))
      }
  }
}
