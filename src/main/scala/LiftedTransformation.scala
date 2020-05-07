import io.scalaland.chimney.TransformerF
import io.scalaland.chimney.dsl._

object LiftedTransformation {
  def foo (pw: String) = pw+"dasda"

  //lifted transformers
  case class RegistrationForm(email: String,
                              username: String,
                              password: String,
                              age: String)

  case class RegisteredUser(email: String,
                            username: String,
                            passwordHash: String,
                            age: Int)

  val okForm = RegistrationForm("john@example.com", "John", "s3cr3t", "40")

  okForm
    .intoF[Option, RegisteredUser] // (1)
    .withFieldComputed(_.passwordHash, form => foo(form.password))
    .withFieldComputedF(_.age, _.age.toIntOption) // (2)
    .transform // (3)

  //As you expect, when provided age which is not valid integer, this code evaluates to None.
  val badForm = RegistrationForm("john@example.com", "John", "s3cr3t", "not an int")

  badForm
    .intoF[Option, RegisteredUser]
    .withFieldComputed(_.passwordHash, form => foo(form.password))
    .withFieldComputedF(_.age, _.age.toIntOption)
    .transform
  // None: Option[RegisteredUser]


  //LIFTED DSL operations: withFieldConstF, withFieldComputedF, witchCoproductInstanceF

  implicit val transformer: TransformerF[Option, RegistrationForm, RegisteredUser] =
    TransformerF.define[Option, RegistrationForm, RegisteredUser]
      .withFieldComputed(_.passwordHash, form => foo(form.password))
      .withFieldComputedF(_.age, _.age.toIntOption)
      .buildTransformer

  Array(okForm, badForm).transformIntoF[Option, List[RegisteredUser]]

  //TransformerF type class(рассказать)
  /*
    trait TransformerF[F[+_], From, To] {
      def transform(src: From): F[To]
    }
   */
  //Supporting custom F[_](рассказать)
  /*
  trait TransformerFSupport[F[+_]] {
  def pure[A](value: A): F[A]
  def product[A, B](fa: F[A], fb: => F[B]): F[(A, B)]
  def map[A, B](fa: F[A], f: A => B): F[B]
  def traverse[M, A, B](it: Iterator[A], f: A => F[B])(implicit fac: Factory[B, M]): F[M]
}
   */
  //Deriving lifted transformers (рассказать)
}