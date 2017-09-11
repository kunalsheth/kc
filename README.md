# KC
A fast and type-safe configuration library for Kotlin.

### Gradle Installation
```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

def kcVersion = '+'

dependencies {
    compile group: 'com.github.kunalsheth', name: 'kc', version: kcVersion
}
```

### Usage
- The KC object manages all configuration values.
- Delegate "static" properties to KC using `kc(default: T)`
```kotlin
fun main(args: Array<String>) {
    MySingleton.myString = "modification one"
    assert(MySingleton.myString == KC[MySingleton::myString])
    
    KC[MySingleton::myString] = "modification two"
    assert(MySingleton.myString == KC[MySingleton::myString])
    
    KC["info.kunalsheth.kc.MySingleton.myString"] = "modification three"
    assert(MySingleton.myString == KC[MySingleton::myString])
}

object MySingleton {
    val myDouble by kc(PI)
    val myFloat by kc(PI.toFloat())
    val myLong by kc(1234567891011121314)
    val myInt by kc(123456789)
    val myChar by kc(Typography.cent)
    val myShort by kc(12345.toShort())
    val myByte by kc(123.toByte())
    var myString by kc("Hello World")
}
```

### Todo List
- [x] Make it work.
- [ ] Add "composite type" Stringifiers. (e.g. handle `Pair<A,B>` when `A` and `B` are already registered)
- [ ] Publish on Maven Central.