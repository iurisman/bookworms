package urisman.bookworms

import akka.http.scaladsl.model._
import org.scalatest.OptionValues
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.matchers.should.Matchers
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.wordspec.AnyWordSpec
import urisman.bookworms.db.Postgres

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext

class DatabaseTest extends DatabaseSpec {

  "Postgres.getBooks()" should {
    "return return all books" in {
      whenReady(Postgres.getBooks) { seq =>
        seq.size shouldBe 5
        seq.find(_.title == "Programming in Scala, Fifth Edition") shouldBe Symbol("defined")
        seq.find(_.title == "Bouchon Bakery") shouldBe None
      }
    }
  }


}