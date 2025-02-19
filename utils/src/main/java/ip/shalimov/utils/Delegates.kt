package ip.shalimov.utils

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object Delegates {

    inline fun <reified T>SharedPreferences.prefs(key: String, defaultValue: T): ReadWriteProperty<Any?, T> {
        return when (T::class) {
            String::class -> StringSharedPrefsDelegate(this, key, defaultValue as String)
            Int::class -> IntSharedPrefsDelegate(this, key, defaultValue as Int)
            Long::class -> LongSharedPrefsDelegate(this, key, defaultValue as Long)
            else -> throw IllegalArgumentException("Unsupported type")
        } as ReadWriteProperty<Any?, T>
    }
}

@PublishedApi
internal class StringSharedPrefsDelegate(
    private val sharedPrefs: SharedPreferences,
    private val key: String? = null,
    private val defaultValue: String? = null
): ReadWriteProperty<Any?, String?> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): String? {
        return sharedPrefs.getString(key ?: property.name, defaultValue)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String?) {
        sharedPrefs.edit { putString(key ?: property.name, value) }
    }

}

@PublishedApi
internal class IntSharedPrefsDelegate(
    private val sharedPrefs: SharedPreferences,
    private val key: String? = null,
    private val defaultValue: Int? = null
): ReadWriteProperty<Any?, Int?> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): Int? {
        return sharedPrefs.getInt(key ?: property.name, defaultValue ?: 0)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int?) {
        sharedPrefs.edit { putInt(key ?: property.name, value ?: 0) }
    }
}

@PublishedApi
internal class LongSharedPrefsDelegate(
    private val sharedPrefs: SharedPreferences,
    private val key: String? = null,
    private val defaultValue: Long? = null
): ReadWriteProperty<Any?, Long?> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): Long? {
        return sharedPrefs.getLong(key ?: property.name, defaultValue ?: 0L)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Long?) {
        sharedPrefs.edit { putLong(key ?: property.name, value ?: 0L) }
    }
}