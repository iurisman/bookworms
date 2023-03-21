package urisman

import io.circe.Decoder.Result
import io.circe._
import io.circe.generic.semiauto._

import java.sql.Date

package object bookworms {

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

  case class Author(id: Int, first: String, last: String)
  object Author {
    implicit val codec: Codec[Author] = deriveCodec[Author]
  }

  case class Book(id: Int, isbn: String, title: String, pubDate: java.sql.Date, copies: Int, authors: Seq[Author])
  object Book {
    implicit val codec: Codec[Book] = deriveCodec[Book]
  }

  //// Exceptions
  case class JsonDecodeException(source: String, target: Class[_])
    extends Exception(s"""Unable to decode JSON string '$source' as class ${target.getName}""")
}
