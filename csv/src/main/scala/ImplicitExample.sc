def greet(name: String)(implicit greeting: String) {
  println(s"$greeting, $name")
}

implicit val greeting = "Hello"

greet("Cliff") // Hello, Cliff