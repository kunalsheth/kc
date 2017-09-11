package info.kunalsheth.kc

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by kunal on 9/7/17.
 */
interface DelegateProvider<in R, T> {
    operator fun provideDelegate(
            thisRef: R,
            property: KProperty<*>
    ): ReadWriteProperty<R, T>
}