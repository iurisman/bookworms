package urisman.bookworms.api

import akka.http.scaladsl.model.HttpResponse
import urisman.bookworms._
import urisman.bookworms.db.AbstractDatabase.database

import scala.concurrent.{ExecutionContext, Future}

object Books extends Endpoint {

  /** Get summaries on all books */
  def get(implicit ec: ExecutionContext): Future[HttpResponse] =
    database.getBooks.map(books => respondOk(books))

  /** Get a book's details */
  def get(bookId: Int)(implicit ec: ExecutionContext): Future[HttpResponse] =
    database.getBookDetails(bookId).map(copies => respondOk(copies))

  /** Add a new book -- UNIMPLEMENTED */
  def post(book: Book): Future[HttpResponse] = {
    Future.successful(respondBadRequest("Unimplemented"))
  }
}
