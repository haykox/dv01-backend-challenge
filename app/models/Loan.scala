package models

object Loan {

  type Loan = Map[String, String]

  def fieldValue(loans: Stream[Map[String, String]], field: Field): String = field match {
    case PlainField(fieldName) => loans.head(fieldName)
    case AggregateField(fieldName, aggregate) =>
      val values = loans.map(_(fieldName).toDouble)
      val aggregateValue = aggregate match {
        case Count => loans.length
        case Min => values.min
        case Max => values.max
        case Sum => values.sum
        case Avg => values.sum / loans.length
      }
      aggregateValue.toString
  }

  def conditionHeld(loan: Map[String, String], condition: Condition): Boolean = {
    val fieldValue = loan(condition.field).toDouble
    condition.operator match {
      case `=` => fieldValue == condition.value
      case `!==` => fieldValue != condition.value
      case `>` => fieldValue > condition.value
      case `<` => fieldValue < condition.value
      case `>=` => fieldValue >= condition.value
      case `<=` => fieldValue <= condition.value
    }
  }

}
