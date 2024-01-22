package codecs

import models._
import cats.syntax.functor._
import io.circe.Decoder
import io.circe.generic.auto._
import io.circe.generic.extras.semiauto.deriveEnumerationDecoder

object JsonCodecs {

  implicit val aggregateDecoder: Decoder[Aggregate] = deriveEnumerationDecoder[Aggregate]
  implicit val operatorDecoder: Decoder[Operator] = deriveEnumerationDecoder[Operator]
  implicit val fieldDecoder: Decoder[Field] = Decoder[AggregateField].widen or Decoder[PlainField].widen

}
