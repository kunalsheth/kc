package info.kunalsheth.kc

import com.google.gson.GsonBuilder

/**
 * Created by kunal on 9/7/17.
 */
fun main(args: Array<String>) {
    MySingleton

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
}


val gson = GsonBuilder()
        .setPrettyPrinting()
        .create()!!

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
    println(gson.toJson(KC.config))
    println()
}