import shapeless.{HList, ::, HNil}
import shapeless.Lazy
import shapeless.Generic

// our friends from representations
final case class Employee(
  name: String,
  number: Int,
  manager: Boolean)

final case class IceCream(
  name: String,
  numCherries: Int,
  inCone: Boolean)

// this is a type class [concept from Haskell... not a scala class]
trait CsvEncoder[A] {
  def encode(value: A): List[String]
}

object CsvEncoder {
  // helper - reduces some boilerplate; makes passed function into a CSV encoder
  def pure[A](func: A => List[String]): CsvEncoder[A] =
    new CsvEncoder[A] {
      def encode(value: A): List[String] =
        func(value)
    }

  // hand implemented encoders...
  implicit val employeeEnc: CsvEncoder[Employee] =
    pure(e => List(
      e.name,
      e.number.toString,
      if (e.manager) "yes" else "no"
    ))

  // ....lots of boiler plate
  implicit val iceCreamEnc: CsvEncoder[IceCream] =
    pure(e => List(
      e.name,
      e.numCherries.toString,
      if (e.inCone) "yes" else "no"
    ))
}

object Main extends Demo {
  def encodeCsv[A](value: A)(implicit enc: CsvEncoder[A]): String =
    enc.encode(value).mkString(",")

  val employee = Employee("Bill", 1, true)
  val iceCream = IceCream("Cornetto", 0, true)

  println(encodeCsv(employee))
  println(encodeCsv(iceCream)(CsvEncoder.iceCreamEnc))
}