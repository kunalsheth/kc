package info.kunalsheth.kc

/**
 * Created by kunal on 9/10/17.
 */
object MySingleton {
    val myDouble by kc(Math.PI)
    val myFloat by kc(Math.PI.toFloat())
    val myLong by kc(1234567891011121314)
    val myInt by kc(123456789)
    val myChar by kc(Typography.cent)
    val myShort by kc(12345.toShort())
    val myByte by kc(123.toByte())
    var myString by kc("Hello KC")
}