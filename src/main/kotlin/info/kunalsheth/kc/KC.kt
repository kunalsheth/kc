package info.kunalsheth.kc

import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.javaField

/**
 * Created by kunal on 9/6/17.
 */
object KC {
    @Suppress("UNCHECKED_CAST")
    operator fun <T : Any> get(key: KProperty<T>)
            = data[key.key1]!![key.key2]!!.value as T

    operator fun <T : Any> set(key: KProperty<T>, value: T) {
        data[key.key1]!![key.key2]!!.unsafeSet(value)
    }

    var config
        get() = data.mapValues { (_, map) ->
            map.mapValues { (_, delegate) ->
                delegate.value
            }
        }
        set(value) = value.forEach { key1, map ->
            map.forEach { key2, delegate ->
                data[key1]!![key2]!!.unsafeSet(delegate)
            }
        }
}

fun <R : Any, T : Any> kc(default: T) = object : DelegateProvider<R, T> {
    override fun provideDelegate(thisRef: R, property: KProperty<*>): KcDelegate<R, T> {
        val delegate = KcDelegate<R, T>(default)

        data.getOrPut(
                property.key1,
                { ConcurrentHashMap() }
        )[property.key2] = delegate

        return delegate
    }
}

private val data = ConcurrentHashMap<String, ConcurrentHashMap<String, KcDelegate<*, *>>>()

private val KProperty<*>.key1
    get() = javaField!!.declaringClass.kotlin.qualifiedName!!
private val KProperty<*>.key2
    get() = name