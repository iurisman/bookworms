package urisman.bookworms.db

import slick.jdbc.PostgresProfile.api._
import urisman.bookworms.model.Book

import scala.concurrent.Future
object Postgres {

  private val db = {

    Database.forConfig("db")
  }

  def getBooks(): Future[Seq[Book]] = {
    sql"""SELECT id, isbn, title, pub_date FROM books""".as[Seq[Book]]
  }
}
