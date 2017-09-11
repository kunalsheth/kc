package info.kunalsheth.kc

import java.util.concurrent.ConcurrentHashMap
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.javaGetter

/**
 * Created by kunal on 9/6/17.
 */
private typealias Pusher = (String) -> Unit
private typealias Puller = () -> String

object KC {
    fun toMap() = pull.mapValues { (_, v) -> v() }

    operator fun get(key: String) = pull[key]!!()
    operator fun set(key: String, value: String) = push[key]!!(value)

    inline operator fun <reified T : Any> get(key: KProperty<T>): T
            = Stringifier[T::class][this[key.qualifiedName]]

    inline operator fun <reified T : Any> set(key: KMutableProperty<T>, value: T) {
        this[key.qualifiedName] = Stringifier[T::class][value]
    }

    private val push = ConcurrentHashMap<String, Pusher>()
    private val pull = ConcurrentHashMap<String, Puller>()

    fun hook(key: String, pusher: Pusher, puller: Puller) {
        push += key to pusher
        pull += key to puller
    }
}

val KProperty<*>.qualifiedName
    get() = "${javaGetter!!.declaringClass.kotlin.qualifiedName}.$name"

inline fun <R, reified T : Any> kc(default: T) = object : DelegateProvider<R, T> {

    override fun provideDelegate(thisRef: R, property: KProperty<*>) = object : ReadWriteProperty<R, T> {
        init {
            KC.hook(
                    property.qualifiedName,
                    { value = Stringifier[T::class][it] },
                    { Stringifier[T::class][value] }
            )
        }

        var value: T = default
        override fun getValue(thisRef: R, property: KProperty<*>) = value
        override fun setValue(thisRef: R, property: KProperty<*>, value: T) {
            this.value = value
        }
    }
}