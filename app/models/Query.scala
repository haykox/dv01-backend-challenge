package models

case class Query(fields: List[Field], conditions: List[Condition], groupBy: List[String])

sealed trait Field
case class PlainField(name: String) extends Field
case class AggregateField(name: String, aggregate: Aggregate) extends Field

sealed trait Aggregate
case object Count extends Aggregate
case object Min extends Aggregate
case object Max extends Aggregate
case object Sum extends Aggregate
case object Avg extends Aggregate

case class Condition(field: String, operator: Operator, value: Double)

sealed trait Operator
case object `=` extends Operator
case object `!==` extends Operator
case object `>` extends Operator
case object `<` extends Operator
case object `>=` extends Operator
case object `<=` extends Operator
