import io.scalaland.chimney.dsl._

object BasicTransformation extends App {

  case class Caterpillar (size: Int, name: String)
  case class Butterfly(size: Int, name: String)

  val tommy = Caterpillar(5, "Tom")
  val tom = tommy.transformInto[Butterfly]

  println(tommy)
  println(tom)

  case class Youngs(insects: List[Caterpillar])
  case class Adults(insects: List[Butterfly])

  val kindergarden = Youngs(List(Caterpillar(5, "Tom"), Caterpillar(4, "Joe")))
  val highschool = kindergarden.transformInto[Adults]

  println(kindergarden)
  println(highschool)

}
