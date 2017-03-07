
trait FourLeggedAnimal {
  def walk() = {
    println("I'm walking")
  }
  def run(afterBall: Boolean) = {
    println("I'm running" + (if (afterBall) " after a ball." else "."))
  }
}

trait FriendlyAnimal {
  val isHappyToSeeYou = true
}

sealed abstract class Pet
case class Cat(name: String) extends Pet with FourLeggedAnimal
case class Dog(name: String) extends Pet with FourLeggedAnimal with FriendlyAnimal
case class Seal(name: String) extends Pet with FriendlyAnimal

val mutley = Dog("Mutley")

mutley.walk()
mutley.run(afterBall = true)
mutley.isHappyToSeeYou

class Bird(name: String) extends FriendlyAnimal {
  override def toString: String = name
}

object Bird {
  def apply(name: String) = new Bird(name)
}

val polly = Bird("Polly") // polly: Bird = Polly

polly.isHappyToSeeYou


class Dawg(name: String) extends FriendlyAnimal with FourLeggedAnimal

val blueDawg = new Dawg("Blue")