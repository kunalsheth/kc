package info.kunalsheth.kc

/**
 * Created by kunal on 9/7/17.
 */
fun main(args: Array<String>) {
    println("Startup")
    printAll()

    print("\n\n\n")

    MySingleton.myString = "modified 1"
    println("""MySingleton.myString = "modified 1"""")
    assert(MySingleton.myString == KC[MySingleton::myString])
    printAll()

    print("\n\n\n")

    KC[MySingleton::myString] = "modified 2"
    println("""KC[MySingleton::myString] = "modified 2"""")
    assert(MySingleton.myString == KC[MySingleton::myString])
    printAll()

    print("\n\n\n")

    KC["info.kunalsheth.kc.MySingleton.myString"] = "modified 3"
    println("""KC["info.kunalsheth.kc.MySingleton.myString"] = "modified 3"""")
    assert(MySingleton.myString == KC[MySingleton::myString])
    printAll()
}


fun printAll() {
    println("""MySingleton:
    myDouble = ${MySingleton.myDouble}
    myFloat = ${MySingleton.myFloat}
    myLong = ${MySingleton.myLong}
    myInt = ${MySingleton.myInt}
    myChar = ${MySingleton.myChar}
    myShort = ${MySingleton.myShort}
    myByte = ${MySingleton.myByte}
    myString = ${MySingleton.myString}""")

    println()

    println("KC:")
    KC.toMap().forEach { k, v -> println("\t$k = $v") }
    println()
}