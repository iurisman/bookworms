package urisman.bookworms.db

import io.circe._
import io.circe.parser._
import slick.jdbc.PostgresProfile.api._
import urisman.bookworms._
import urisman.bookworms.db.codegen.Tables._

import scala.concurrent.{ExecutionContext, Future}
final class BookwormsDatabase(implicit ec: ExecutionContext) {

  private val postgres = Database.forConfig("bookworms.db")

  def getBooks: Future[Seq[Book]] = {
    postgres.run(
      sql"""
            SELECT
              b.id, b.isbn, b.title, b.pub_date,
              (
                SELECT
                COUNT(*) FROM copies c WHERE c.book_id = b.id AND c.available
              ) AS "count",
              (
                SELECT json_agg(json_build_object('id', a.id, 'first', a.first, 'last', a.last))
                FROM authors a, book_authors ba WHERE ba.book_id = b.id AND ba.author_id = a.id)
            FROM books b;
            """.as[(Int, String, String, java.sql.Date, Int, String)])
      .map { _.map {
        case (id, isdn, title, pubDate, copies, authorsJson) =>
          val authors = decode[Seq[Author]](authorsJson) match {
            case Left(error) => throw JsonDecodeException(authorsJson, classOf[Author])
            case Right(result) => result
          }
          Book(id, isdn, title, pubDate, copies, authors)
      }}
  }
}
