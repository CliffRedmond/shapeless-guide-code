
val list1: List[Int] = List[Int](1, 2, 3)
val list2 = List(4, 5, 6)
val list3 = 7 :: 8 :: 9 :: Nil
val list4 = List.apply(10, 11, 12) // under-the-hood
val list5 = 13 :: 14 :: List() // just weird

def sum(list: List[Int]): Int =
  list match {
    case head :: tail => head + sum(tail)
    case Nil => 0
  }

val result = sum(0 :: list1 ::: list2) // 21

val infixBracket = (0) :: List(1,2,3)
val postFixBracket = List(1,2,3).::(0)
val infix = 0 :: List(1,2,3)
val basic = List(1,2,3,4)

//assert(infixBracket == postFixBracket)
//assert(false)

val combinedInfix = 0 :: list1 ::: list2
val combinedPostfix = (list2.:::(list1)).::(0)

sum(combinedInfix)

def anotherSum(list: List[Int]): Int =
  list match {
    case ::(head,tail) => head + sum(tail)
    case Nil => 0
  }








































/*
def printList[T](list: List[T]): Unit = {
  list match {
    case head::tail => {
      println(head)
      printList(tail)
    }
    case Nil => println("<end>")
  }
}

printList(0 :: list1 ::: list2 ++ list3)
*/