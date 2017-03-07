//import scala.language.postfixOps

val x: String = "A Sample String" // A Sample String
val y = "A Sample String" // A Sample String
val z = "A LowerCase String" toLowerCase // a lowercase string
val z1 = "A LowerCase String".toLowerCase() // a lowercase string

sealed abstract class A
case class X(name: String) extends A
case class Y(name: String) extends A

def isA(a: A) = a match { case _ => "Got an A" }
