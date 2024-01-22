package controllers

import codecs.JsonCodecs._
import models._
import models.Loan._
import com.github.tototoshi.csv.CSVReader
import io.circe.generic.auto._
import io.circe.parser.decode

import java.io.InputStreamReader
import javax.inject._
import play.api.libs.json.Json
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) (implicit assetsFinder: AssetsFinder)
  extends AbstractController(cc) {

  private final val filePath = "/data/LoanStats_securev1_2017Q4.csv"
  private final val loans = loadLoans(filePath)

  def index = Action {
    Ok(Json.toJson(loans.head.keys))
  }

  def insights = Action { request =>
    val query = request.body.asText.getOrElse("{}")
    decode[Query](query) match {
      case Left(decodeError) => BadRequest(decodeError.getMessage)
      case Right(query) =>
        val result = getInsightsFromData(query, loans)
        Ok(Json.toJson(result))
    }
  }

  private def loadLoans(filePath: String) = {
    val inputStream = this.getClass.getResourceAsStream(filePath)
    CSVReader
      .open(new InputStreamReader(inputStream))
      .toStreamWithHeaders
  }

  private def getInsightsFromData(query: Query, loans: Stream[Loan]): Stream[List[String]] = {
    loans
      .filter(loan => query.conditions.forall(conditionHeld(loan, _)))
      .groupBy(loan => query.groupBy.map(field => loan(field)))
      .mapValues(loans => query.fields.map(field => fieldValue(loans, field)))
      .values
      .toStream
  }

}
