package urisman

import io.circe.Decoder.Result
import io.circe._
import io.circe.generic.semiauto._
import io.circe.parser.parse

import java.sql.Date
import scala.util.Try

package object bookworms {

  //// Data transfer case classes
  case class Author(id: Int, first: String, last: String)
  object Author {
    implicit val codec: Codec[Author] = deriveCodec[Author]
  }

  case class Book(id: Int, isbn: String, title: String, pubDate: java.sql.Date, coverImageUri: String, copies: Int, authors: Seq[Author])
  object Book {
    implicit val codec: Codec[Book] = deriveCodec[Book]
  }

  case class Copy(id: Int, bookId: Int, condition: String, price: BigDecimal, location: String, available: Boolean)
  object Copy {
    implicit val codec: Codec[Copy] = deriveCodec
  }

  case class BookDetails(book: Book, availableCopies: Seq[Copy])

  object BookDetails {
    implicit val coded: Codec[BookDetails] = deriveCodec
  }

  case class Receipt(price: String, tax: String, shipping: String, total: String)

  object Receipt {
    implicit val coded: Codec[Receipt] = deriveCodec
  }

  //// Exceptions
  case class JsonDecodeException(source: String, target: Class[_])
    extends Exception(s"""Unable to decode JSON string '$source' as class ${target.getName}""")

  //// JSON marshalling
  implicit val e1: Codec[java.sql.Date] = new Codec[java.sql.Date]() {
    // Encoder
    override def apply(sqlDate: java.sql.Date): Json =
      Encoder.encodeString(sqlDate.toString)

    // Decoder
    override def apply(c: HCursor): Result[Date] = {
      Decoder.decodeString.map(str => java.sql.Date.valueOf(str)).apply(c)
    }
  }

  def parseAndThen[T, R](json: String)(mapper: T => R)(implicit decoder: Decoder[T]): Try[R] = {
    for {
      json <- parse(json)
      t <- json.as[T]
    } yield mapper(t)
  }.toTry
}
