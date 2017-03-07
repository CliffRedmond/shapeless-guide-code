import shapeless._

final case class IceCream(name: String, numCherries: Int, inCone: Boolean)

type IceCreamRepr =
  ::[String, ::[Int, ::[Boolean, HNil]]]

val iceCream: IceCreamRepr =
  "Sundae" :: 1 :: false :: HNil

type IceCreamRepr2 = String :: Int :: Boolean :: HNil

val iceCream2 = IceCream("Sundae",1,false)

assert(Generic[IceCream].to(iceCream2) == iceCream)
