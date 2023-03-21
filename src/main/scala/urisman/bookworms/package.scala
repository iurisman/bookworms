package urisman

import io.circe._, io.circe.generic.semiauto._

package object bookworms {

  case class Author(id: Int, first: String, last: String)
  object Author {
    implicit val decoder: Decoder[Author] = deriveDecoder[Author]
    implicit val encoder: Encoder[Author] = deriveEncoder[Author]
  }

  //case class Book(id: Int, isbn: String, title: String, pubDate: java.sql.Date, copies: Int, authors: Seq[Author])
  case class Book(id: Int, isbn: String, title: String, copies: Int)
//    object Book {
//      implicit val decoder: Decoder[Book] = deriveDecoder
//      implicit val encoder: Encoder[Book] = deriveEncoder
//    }

  case class JsonDecodeException(source: String, target: Class[_])
    extends Exception(s"""Unable to decode JSON string '$source' as class ${target.getName}""")
}
