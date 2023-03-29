package urisman.bookworms.api

import akka.http.scaladsl.model.HttpResponse
import urisman.bookworms._

import java.text.{FieldPosition, NumberFormat, ParsePosition}
import java.util.Locale
import scala.concurrent.{ExecutionContext, Future}
import scala.math.BigDecimal.RoundingMode
import scala.util.Random

object Copies extends Endpoint {

  def receiptFor(copy: Copy): Receipt = {
    val price = copy.price.setScale(2)
    val tax = copy.price * BigDecimal(0.097).setScale(2, RoundingMode.HALF_EVEN)
    val shipping = BigDecimal(
      // Simulate shipping as a stable random fraction of the price.
      new Random(copy.price.longValue).nextFloat() * price.doubleValue
    ).setScale(2, RoundingMode.HALF_EVEN)
    val total = price + tax + shipping
    val format = NumberFormat.getCurrencyInstance(Locale.US)
    Receipt(format.format(price), format.format(tax), format.format(shipping), format.format(total))
  }

  /**
   * Hold a book and return receipt.
   * In real life we'd update the book record to make it unavailable for a limited period
   * of time to avoid shopping cart collisions. An async process would be needed to clear
   * holds on books that didn't end up being purchased.
   */
  def hold(copyId: Int)(implicit ec: ExecutionContext): Future[HttpResponse] =
    Postgres.getCopy(copyId)
      .map {
        case Some(copy) => respondOk(receiptFor(copy))
        case None => respondBadRequest(s"No copy with ID $copyId")
      }
  def update(copy: Copy)(implicit ec: ExecutionContext): Future[HttpResponse] =
    Postgres.updateCopy(copy)
      .map {
        case true => respondNoContent()
        case false => respondBadRequest(s"No copy with ID ${copy.id}")
      }

}
