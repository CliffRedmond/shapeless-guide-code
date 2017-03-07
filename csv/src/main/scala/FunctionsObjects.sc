
// every value is an object
// every function is a value
// => every function is an object
val power: (Int, Int) => Int =
  (base: Int, exp: Int) =>
    if (exp <= 1) {
      base
    } else {
      base * power(base, exp - 1)
    }

val x = power(2,2) // short cut for apply
val y = power.apply(2,2)

def squared(base: Int) : Int = power(base, 2)

val cubed: (Int) => Int = (base: Int) => power(base, 3)

val x1 = squared(2)
val y2 = cubed(2)



