package urisman.bookworms.api

import io.circe._
import io.circe.syntax._
import urisman.bookworms.db.BookwormsDatabase

import scala.concurrent.{ExecutionContext, Future}

object Books {

  def get(implicit ec: ExecutionContext): Future[Json] =
    BookwormsDatabase.getBooks.map(books => books.asJson)
}
