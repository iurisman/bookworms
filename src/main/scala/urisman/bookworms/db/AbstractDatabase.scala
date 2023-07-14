package urisman.bookworms.db

import io.circe.parser._
import slick.jdbc.PostgresProfile.api._
import urisman.bookworms.{Author, Book, BookDetails, Copy, JsonDecodeException}

import scala.concurrent.{ExecutionContext, Future}

/**
 * Database access methods.
 */
trait AbstractDatabase {

  /** Get all books */
  def getBooks(implicit ec: ExecutionContext): Future[Seq[Book]]

  /** Get a book by ID */
  def getBook(bookId: Int)(implicit ec: ExecutionContext): Future[Option[Book]]

  def getBookDetails(bookId: Int)(implicit ec: ExecutionContext): Future[Option[BookDetails]]

  /** Get all copies of a book */
  def getCopiesOf(bookId: Int)(implicit ec: ExecutionContext): Future[Seq[Copy]]

  /** Get a copy by id */
  def getCopy(copyId: Int)(implicit ec: ExecutionContext): Future[Option[Copy]]

  /** Update a book copy, returning true if successful or false otherwise */
  def updateCopy(copy: Copy)(implicit ec: ExecutionContext): Future[Boolean]
}

object AbstractDatabase {
  lazy val database: AbstractDatabase = Postgres
}
