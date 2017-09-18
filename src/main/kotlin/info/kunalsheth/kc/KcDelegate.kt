package info.kunalsheth.kc

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by kunal on 9/16/17.
 */
class KcDelegate<in R, T : Any>(default: T)
    : ReadWriteProperty<R, T> {

    val type = default::class

    var value: T = default

    @Suppress("UNCHECKED_CAST")
    fun unsafeSet(value: Any) {
        this.value = value as T
    }

    override fun getValue(thisRef: R, property: KProperty<*>) = value
    override fun setValue(thisRef: R, property: KProperty<*>, value: T) {
        this.value = value
    }
}