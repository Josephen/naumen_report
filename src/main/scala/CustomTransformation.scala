import io.scalaland.chimney.dsl._

object CustomTransformation extends App {

  //missing values
  case class Caterpillar(size: Int, name: String)
  case class Butterfly(size: Int, name: String, wingsColor: String)

  val tommy = Caterpillar (5, "tom")

  //val tom = tommy.transformInto[Butterfly]

  /*
  Error:(11, 32) Chimney can't derive transformation from CustomTransformation.Caterpillar to CustomTransformation.Butterfly

  CustomTransformation.Butterfly

  wingsColor: java.lang.String - no accessor named wingsColor in source type CustomTransformation.Caterpillar
   */

  //  val tom = tommy.transformInto[Butterfly]
  val tomConst = tommy.into[Butterfly]
    .withFieldConst(_.wingsColor, "white")
    .transform

  val tomComputed = tommy.into[Butterfly]
    .withFieldComputed(_.wingsColor, c => if (c.size > 4) "yellow" else "black")
    .transform

  println(tommy)
  println(tomConst)
  println(tomComputed)

  //rename fields

  case class SpyEN(name: String, surname: String)
  case class SpyRU(imya: String, familiya: String)

  val johnEN = SpyEN("John", "Doe")

  val johnRU = johnEN.into[SpyRU]
    .withFieldRenamed(_.name, _.imya)
    .withFieldRenamed(_.surname, _.familiya)
    .transform

  println(johnEN)
  println(johnRU)
}


