import io.scalaland.chimney.dsl._

object BasicTransformation extends App {

  case class Caterpillar (size: Int, name: String)
  case class Butterfly(size: Int, name: String)

  val tommy = Caterpillar(5, "Tom")
  val tom = tommy.transformInto[Butterfly]

  println(tommy)
  println(tom)
}
