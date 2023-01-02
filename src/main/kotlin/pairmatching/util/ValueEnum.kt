package pairmatching.util

interface ValueEnum<E : Enum<E>> {
    val value: String

    fun asString(): String = value
}


inline fun <reified E> valueEnumOf(value: String): E
    where E : Enum<E>,
          E : ValueEnum<E>
{
    return requireNotNull(
        enumValues<E>().find { it.value == value }
    ) {
        "${E::class.java} is not contains $value"
    }
}

inline fun <reified E> valueEnumContains(value: String): Boolean
        where E : Enum<E>,
              E : ValueEnum<E>
{
    return enumValues<E>().any { it.value == value }
}