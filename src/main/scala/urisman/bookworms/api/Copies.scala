package urisman.bookworms.api

import akka.http.scaladsl.model.HttpResponse
import urisman.bookworms.db.BookwormsDatabase
import urisman.bookworms._

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random

object Copies extends Endpoint {

  def receiptFor(copy: Copy): Receipt = {
    import java.math.{MathContext, RoundingMode}
    val mathCtx = new MathContext(2, RoundingMode.HALF_EVEN)
    val price = copy.price(mathCtx)
    val tax = copy.price * BigDecimal(0.097)
    val shipping = BigDecimal(Random.nextFloat() * price.doubleValue)(mathCtx)
    val total = price + tax + shipping
    Receipt(price.toString, tax.toString, shipping.toString, total.toString)
  }

  /**
   * Hold a book and return receipt.
   * In real life we'd update the book record to make it unavailable for a limited period
   * of time to avoid shopping cart collisions. An async process would be needed to clear
   * holds on books that didn't end up being purchased.
   */
  def hold(copyId: Int)(implicit ec: ExecutionContext): Future[HttpResponse] =
    BookwormsDatabase.getCopy(copyId)
      .map {
        case Some(copy) => okResponse(receiptFor(copy))
        case None => badRequestResponse(s"No copy with ID $copyId")
      }


}
