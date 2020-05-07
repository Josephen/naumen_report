import java.time.ZonedDateTime

import scala.util.Random

object Chimney {

  case class MakeCoffee(id: Int, kind: String, addict: String)
  case class CoffeeMade(id: Int, kind: String, forAddict: String, at: ZonedDateTime)

  val command = MakeCoffee(id = Random.nextInt,
    kind = "Espresso",
    addict = "Piotr")
  val event = CoffeeMade(id = command.id,
    kind = command.kind,
    forAddict = command.addict,
    at = ZonedDateTime.now)

  import io.scalaland.chimney.dsl._

  val eventRight = command.into[CoffeeMade]
    .withFieldComputed(_.at, _ => ZonedDateTime.now)
    .withFieldRenamed(_.addict, _.forAddict)
    .transform
}
