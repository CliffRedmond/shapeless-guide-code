import shapeless.{ ::, Generic, HList, HNil }

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

  // add helper encoders 
  implicit val stringEncoder: CsvEncoder[String] =
    pure(str => List(str))

  implicit val intEncoder: CsvEncoder[Int] =
    pure(num => List(num.toString))

  implicit val booleanEncoder: CsvEncoder[Boolean] =
    pure(bool => List(if (bool) "yes" else "no"))

  // will instead declare type classes for HLists; need two instances

  // Empty HList
  implicit val hnilEnc: CsvEncoder[HNil] =
    pure(hnil => Nil)

  // Non-Empty HList
  implicit def hlistEnc[H, T <: HList ](
    implicit
    hEnc: CsvEncoder[H],
    tEnc: CsvEncoder[T]
  ): CsvEncoder[H :: T] =
    pure {
      case h :: t =>
        hEnc.encode(h) ++ tEnc.encode(t)
    }

  // now we declare a generic encoder
  implicit def genericEnc[A,R](
    implicit
    gen: Generic[A] {type Repr = R /*a type refinement to link R to Repr*/}, // will turn A into an HList
    enc: CsvEncoder[R] // will turn HList into CSV
  ): CsvEncoder[A] =
    pure(a => enc.encode(gen.to(a)))

}

object Main extends Demo {
  def encodeCsv[A](value: A)(implicit enc: CsvEncoder[A]): String =
    enc.encode(value).mkString(",")

  val employee = Employee("Bill", 1, true)
  val iceCream = IceCream("Cornetto", 0, true)

  //println(encodeCsv(true))

  println(encodeCsv("Hello" :: "Acme" :: true :: "is" :: 1 :: HNil))

  println(encodeCsv(employee))
  println(encodeCsv(iceCream))

  //println(encodeCsv(employee))
  //println(encodeCsv(iceCream))
}