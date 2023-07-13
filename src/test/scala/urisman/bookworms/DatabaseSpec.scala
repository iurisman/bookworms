package urisman.bookworms

import org.scalatest.OptionValues
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.matchers.should.Matchers
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.wordspec.AnyWordSpec

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext

class DatabaseSpec extends AnyWordSpec with Matchers with ScalaFutures with OptionValues {

  implicit protected val ec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(2))

  implicit override val patienceConfig:PatienceConfig =
    PatienceConfig(timeout = Span(2, Seconds), interval = Span(5, Millis))



}