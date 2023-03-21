package urisman.bookworms.api.model

import java.time.LocalDate

case class Book(id: Int, isbn: String, title: String, pubDate: Option[LocalDate])