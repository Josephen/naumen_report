import io.scalaland.chimney.dsl._

import io.scalaland.chimney.Transformer
/*
trait Transformer[From, To] {
  def transform(src: From): To
}
 */

object OwnTransformation extends App {

  //transformer type class
  object v1 {
    case class User(id: Int, name: String, street: String, postalCode: String)
  }
  object v2 {
    case class Address(street: String, postalCode: String)
    case class User(id: Int, name: String, addresses: List[Address])
  }

  implicit val userV1toV2: Transformer[v1.User, v2.User] =
    (user: v1.User) => v2.User(
      id = user.id,
      name = user.name,
      addresses = List(v2.Address(user.street, user.postalCode))
    )

  val v1Users = List(
    v1.User(1, "Steve", "Love street", "27000"),
    v1.User(2, "Anna", "Broadway", "00321")
  )

  val v2Users = v1Users.transformInto[List[v2.User]]

  println(v2Users )

  //transformer definition DSL

//  implicit val userV2toV2: Transformer[v1.User, v2.User] =
//    Transformer.define[v1.User, v2.User]
//      .withFieldComputed(_.addresses, u => List(v2.Address(u.street, u.postalCode)))
//      .buildTransformer
}
