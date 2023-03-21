package urisman.bookworms.db

import slick.jdbc.PostgresProfile.api._
//import urisman.bookworms.model.Book
import urisman.bookworms.db.codegen.Tables._
import scala.concurrent.Future
object Postgres {

  private val db = Database.forConfig("bookworms.db")

  def getBooks: Future[Seq[BooksRow]] = {
    db.run(
      sql"""SELECT id, isbn, title, pub_date FROM books"""
        .as[BooksRow])
  }
}
