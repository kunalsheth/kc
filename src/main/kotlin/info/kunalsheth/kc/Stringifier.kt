package info.kunalsheth.kc

import kotlin.reflect.KClass

/**
 * Created by kunal on 9/7/17.
 */
typealias Serializer<T> = (T) -> String
typealias Deserializer<T> = (String) -> T


class Stringifier<T>(
        private val i: Deserializer<T>,
        private val o: Serializer<T> = { it.toString() }
) {
    operator fun get(x: T) = o(x)
    operator fun get(x: String) = i(x)

    companion object {
        private val stringifiers = HashMap<KClass<*>, Stringifier<*>>()

        init {
            this[Double::class] = Stringifier({ it.toDouble() })
            this[Float::class] = Stringifier({ it.toFloat() })
            this[Long::class] = Stringifier({ it.toLong() })
            this[Int::class] = Stringifier({ it.toInt() })
            this[Char::class] = Stringifier({ it[0] })
            this[Short::class] = Stringifier({ it.toShort() })
            this[Byte::class] = Stringifier({ it.toByte() })
            this[String::class] = Stringifier({ it })
        }

        operator fun <T : Any> get(x: KClass<T>)
                = stringifiers[x] as Stringifier<T>

        operator fun <T : Any> set(x: KClass<T>, y: Stringifier<T>) {
            stringifiers[x] = y
        }
    }
}