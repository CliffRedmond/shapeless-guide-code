import shapeless.{ ::, Generic, HList, HNil, Lazy }

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

  // will instead declare type classes for HLists; need two instances

  // Empty HList
  implicit val hnilEnc: CsvEncoder[HNil] = ???

  // Non-Empty HList
  implicit def hlistEnc[H, T <: HList ]: CsvEncoder[H :: T] = ???
}

object Main extends Demo {
  def encodeCsv[A](value: A)(implicit enc: CsvEncoder[A]): String =
    enc.encode(value).mkString(",")

  val employee = Employee("Bill", 1, true)
  val iceCream = IceCream("Cornetto", 0, true)

  //println(encodeCsv(employee))
  //println(encodeCsv(iceCream))
}