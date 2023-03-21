package urisman.bookworms.api

import scala.concurrent.{ExecutionContext, Future}
import urisman.bookworms.db.Postgres
import urisman.bookworms.db.codegen.Tables.BooksRow


object Books {

  def get(implicit ec: ExecutionContext): Future[Seq[BooksRow]] = {
    Postgres.getBooks
      .map {
        bookRow => bookRow
      }
  }
}
