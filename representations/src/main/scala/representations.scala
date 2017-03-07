import shapeless.Generic

final case class Employee(name: String, number: Int, manager: Boolean)
final case class IceCream(name: String, numCherries: Int, inCone: Boolean)

object Main extends Demo {
  val employee = Employee("Bill", 1, true)
  val iceCream = IceCream("Cornetto", 0, true)

  val employeeGen = Generic[Employee] // shapeless object
  val iceCreamGen = Generic[IceCream]

  println("Employee: " + employee)
  println("IceCream: " + iceCream)

  println("Employee Repr: " + employeeGen.to(employee)) // to example -> produces HList
  println("IceCream Repr: " + iceCreamGen.to(iceCream))

  val employeeRepr = employeeGen.to(employee)
  val iceCreamRepr = iceCreamGen.to(iceCream)

  val newCoolEmployee: Employee = Generic[Employee].from(iceCreamRepr) // employeeGen.from(iceCreamRepr)

  println(newCoolEmployee)
}
