import io.scalaland.chimney.dsl._

object DefaultValue extends App {

  //automatic value provision
  case class Caterpillar(size: Int, name: String)
  case class Butterfly(size: Int, name: String, wingsColor: String = "purple")

  val tommy = Caterpillar(5, "Tom")

  val tom = tommy.transformInto[Butterfly]

  //default values for option

  case class Foo(a: Int, b: String)
  case class FooV2(a: Int, b: String, newField: Option[Double])

  //.withFieldConst

  Foo(5, "test")
    .into[FooV2]
    .withFieldConst(_.newField, None)
    .transform
  // FooV2(5, "test", None)

  //.enableOptionDefaultsToNone
  Foo(5, "test")
    .into[FooV2]
    .enableOptionDefaultsToNone
    .transform
  // FooV2(5, "test", None)

  //default values for unit

  case class Foo1(x: Int, y: String)
  case class Bar(x: Int, y: String, z: Unit)

  Foo1(10, "test").transformInto[Bar]
  // Foo1(10, test, ())

}
