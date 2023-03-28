package urisman.bookworms.db

import io.circe.parser._
import slick.jdbc.PostgresProfile.api._
import urisman.bookworms._

import scala.concurrent.{ExecutionContext, Future}
object BookwormsDatabase {

  private val postgres = Database.forConfig("bookworms.db")

  def getBooks(implicit ec: ExecutionContext): Future[Seq[Book]] = {
    postgres.run(
      sql"""
            SELECT
              b.id, b.isbn, b.title, b.pub_date, b.cover_image_uri,
              (
                SELECT
                COUNT(*) FROM copies c WHERE c.book_id = b.id AND c.available
              ) AS "count",
              (
                SELECT json_agg(json_build_object('id', a.id, 'first', a.first, 'last', a.last))
                FROM authors a, book_authors ba WHERE ba.book_id = b.id AND ba.author_id = a.id)
            FROM books b;
            """.as[(Int, String, String, java.sql.Date, String, Int, String)])
      .map { _.map {
        case (id, isdn, title, pubDate, coverImageUri, copies, authorsJson) =>
          val authors = decode[Seq[Author]](authorsJson) match {
            case Left(error) => throw JsonDecodeException(authorsJson, classOf[Author])
            case Right(result) => result
          }
          Book(id, isdn, title, pubDate, coverImageUri, copies, authors)
      }}
  }

  def getBook(id: Int)(implicit ec: ExecutionContext): Future[Option[Book]] = {
    postgres.run(
      sql"""
            SELECT
              b.id, b.isbn, b.title, b.pub_date, b.cover_image_uri,
              (
                SELECT
                COUNT(*) FROM copies c WHERE c.book_id = b.id AND c.available
              ) AS "count",
              (
                SELECT json_agg(json_build_object('id', a.id, 'first', a.first, 'last', a.last))
                FROM authors a, book_authors ba WHERE ba.book_id = b.id AND ba.author_id = a.id)
            FROM books b
            WHERE b.id = ${id};
            """.as[(Int, String, String, java.sql.Date, String, Int, String)])
      .map {
        _.headOption.map {
          case (id, isdn, title, pubDate, coverImageUri, copies, authorsJson) =>
            val authors = decode[Seq[Author]](authorsJson) match {
              case Left(error) => throw JsonDecodeException(authorsJson, classOf[Author])
              case Right(result) => result
            }
            Book(id, isdn, title, pubDate, coverImageUri, copies, authors)
        }
      }
  }

  def getBookDetails(bookId: Int)(implicit ec: ExecutionContext): Future[Option[BookDetails]] = {
    for {
      bookOpt <- getBook(bookId)
      copies <- getCopiesOf(bookId)
    } yield bookOpt.map(BookDetails(_, copies))
  }

  /** Get all copies of a book */
  def getCopiesOf(bookId: Int)(implicit ec: ExecutionContext): Future[Seq[Copy]] = {
    postgres.run(
      sql"""
            SELECT id, book_id, condition, price, location, available
            FROM copies
            WHERE book_id = ${bookId} AND available;
            """.as[(Int, Int, String, BigDecimal, String, Boolean)])
      .map {
        _.map {
          case (id, bookId, condition, price, location, isAvailable) =>
            Copy(id, bookId, condition, price, location, isAvailable)
        }
      }
  }
}
