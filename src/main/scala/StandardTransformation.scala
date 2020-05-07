import BasicTransformation.Butterfly
import io.scalaland.chimney.Transformer
import io.scalaland.chimney.dsl._

object StandardTransformation {

  //identity transformation

  1234.transformInto[Int]
  true.transformInto[Boolean]
  3.141.transformInto[Double]
  "test".transformInto[String]
  Butterfly(3, "John").transformInto[Butterfly]

  //supertype transformation

  class Vehicle(val maxSpeed: Double)
  class Car(maxSpeed: Double, val seats: Int) extends Vehicle(maxSpeed)

  (new Car(180, 5)).transformInto[Vehicle]
  Transformer[Car, Vehicle]

  //value class transfomation

  object rich {
    case class PersonId(id: Int) extends AnyVal
    case class PersonName(name: String) extends AnyVal
    case class Person(personId: PersonId, personName: PersonName, age: Int)
  }

  object plain {
    case class Person(personId: Int, personName: String, age: Int)
  }

  import StandardTransformation.rich.{PersonId, PersonName}

  val richPerson = rich.Person(PersonId(10), PersonName("Bill"), 30)
  val plainPerson = richPerson.transformInto[plain.Person]
  val richPerson2 = plainPerson.transformInto[rich.Person]
}
