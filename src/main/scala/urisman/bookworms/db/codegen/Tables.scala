package urisman.bookworms.db.codegen
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.PostgresProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Authors.schema ++ BookAuthors.schema ++ Books.schema ++ Copies.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Authors
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param first Database column first SqlType(varchar), Length(64,true), Default(None)
   *  @param last Database column last SqlType(varchar), Length(64,true) */
  case class AuthorsRow(id: Int, first: Option[String] = None, last: String)
  /** GetResult implicit for fetching AuthorsRow objects using plain SQL queries */
  implicit def GetResultAuthorsRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[String]): GR[AuthorsRow] = GR{
    prs => import prs._
    AuthorsRow.tupled((<<[Int], <<?[String], <<[String]))
  }
  /** Table description of table authors. Objects of this class serve as prototypes for rows in queries. */
  class Authors(_tableTag: Tag) extends profile.api.Table[AuthorsRow](_tableTag, "authors") {
    def * = (id, first, last) <> (AuthorsRow.tupled, AuthorsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), first, Rep.Some(last))).shaped.<>({r=>import r._; _1.map(_=> AuthorsRow.tupled((_1.get, _2, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column first SqlType(varchar), Length(64,true), Default(None) */
    val first: Rep[Option[String]] = column[Option[String]]("first", O.Length(64,varying=true), O.Default(None))
    /** Database column last SqlType(varchar), Length(64,true) */
    val last: Rep[String] = column[String]("last", O.Length(64,varying=true))
  }
  /** Collection-like TableQuery object for table Authors */
  lazy val Authors = new TableQuery(tag => new Authors(tag))

  /** Entity class storing rows of table BookAuthors
   *  @param bookId Database column book_id SqlType(int4)
   *  @param authorId Database column author_id SqlType(int4) */
  case class BookAuthorsRow(bookId: Int, authorId: Int)
  /** GetResult implicit for fetching BookAuthorsRow objects using plain SQL queries */
  implicit def GetResultBookAuthorsRow(implicit e0: GR[Int]): GR[BookAuthorsRow] = GR{
    prs => import prs._
    BookAuthorsRow.tupled((<<[Int], <<[Int]))
  }
  /** Table description of table book_authors. Objects of this class serve as prototypes for rows in queries. */
  class BookAuthors(_tableTag: Tag) extends profile.api.Table[BookAuthorsRow](_tableTag, "book_authors") {
    def * = (bookId, authorId) <> (BookAuthorsRow.tupled, BookAuthorsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(bookId), Rep.Some(authorId))).shaped.<>({r=>import r._; _1.map(_=> BookAuthorsRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column book_id SqlType(int4) */
    val bookId: Rep[Int] = column[Int]("book_id")
    /** Database column author_id SqlType(int4) */
    val authorId: Rep[Int] = column[Int]("author_id")

    /** Foreign key referencing Authors (database name book_authors_author_id_fkey) */
    lazy val authorsFk = foreignKey("book_authors_author_id_fkey", authorId, Authors)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Books (database name book_authors_book_id_fkey) */
    lazy val booksFk = foreignKey("book_authors_book_id_fkey", bookId, Books)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table BookAuthors */
  lazy val BookAuthors = new TableQuery(tag => new BookAuthors(tag))

  /** Entity class storing rows of table Books
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param isbn Database column isbn SqlType(varchar), Length(16,true)
   *  @param title Database column title SqlType(varchar), Length(256,true)
   *  @param pubDate Database column pub_date SqlType(date) */
  case class BooksRow(id: Int, isbn: String, title: String, pubDate: java.sql.Date)
  /** GetResult implicit for fetching BooksRow objects using plain SQL queries */
  implicit def GetResultBooksRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Date]): GR[BooksRow] = GR{
    prs => import prs._
    BooksRow.tupled((<<[Int], <<[String], <<[String], <<[java.sql.Date]))
  }
  /** Table description of table books. Objects of this class serve as prototypes for rows in queries. */
  class Books(_tableTag: Tag) extends profile.api.Table[BooksRow](_tableTag, "books") {
    def * = (id, isbn, title, pubDate) <> (BooksRow.tupled, BooksRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(isbn), Rep.Some(title), Rep.Some(pubDate))).shaped.<>({r=>import r._; _1.map(_=> BooksRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column isbn SqlType(varchar), Length(16,true) */
    val isbn: Rep[String] = column[String]("isbn", O.Length(16,varying=true))
    /** Database column title SqlType(varchar), Length(256,true) */
    val title: Rep[String] = column[String]("title", O.Length(256,varying=true))
    /** Database column pub_date SqlType(date) */
    val pubDate: Rep[java.sql.Date] = column[java.sql.Date]("pub_date")
  }
  /** Collection-like TableQuery object for table Books */
  lazy val Books = new TableQuery(tag => new Books(tag))

  /** Entity class storing rows of table Copies
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param bookId Database column book_id SqlType(int4)
   *  @param condition Database column condition SqlType(varchar), Length(32,true), Default(None)
   *  @param price Database column price SqlType(money), Default(None)
   *  @param available Database column available SqlType(bool) */
  case class CopiesRow(id: Int, bookId: Int, condition: Option[String] = None, price: Option[Double] = None, available: Boolean)
  /** GetResult implicit for fetching CopiesRow objects using plain SQL queries */
  implicit def GetResultCopiesRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Double]], e3: GR[Boolean]): GR[CopiesRow] = GR{
    prs => import prs._
    CopiesRow.tupled((<<[Int], <<[Int], <<?[String], <<?[Double], <<[Boolean]))
  }
  /** Table description of table copies. Objects of this class serve as prototypes for rows in queries. */
  class Copies(_tableTag: Tag) extends profile.api.Table[CopiesRow](_tableTag, "copies") {
    def * = (id, bookId, condition, price, available) <> (CopiesRow.tupled, CopiesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(bookId), condition, price, Rep.Some(available))).shaped.<>({r=>import r._; _1.map(_=> CopiesRow.tupled((_1.get, _2.get, _3, _4, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column book_id SqlType(int4) */
    val bookId: Rep[Int] = column[Int]("book_id")
    /** Database column condition SqlType(varchar), Length(32,true), Default(None) */
    val condition: Rep[Option[String]] = column[Option[String]]("condition", O.Length(32,varying=true), O.Default(None))
    /** Database column price SqlType(money), Default(None) */
    val price: Rep[Option[Double]] = column[Option[Double]]("price", O.Default(None))
    /** Database column available SqlType(bool) */
    val available: Rep[Boolean] = column[Boolean]("available")

    /** Foreign key referencing Books (database name copies_book_id_fkey) */
    lazy val booksFk = foreignKey("copies_book_id_fkey", bookId, Books)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Copies */
  lazy val Copies = new TableQuery(tag => new Copies(tag))
}
