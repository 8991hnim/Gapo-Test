package m.tech.gapotest.business.data.cache

sealed class CacheResult<out T> {

    data class Success<out T>(val value: T): CacheResult<T>()

    data class GenericError(
        val errorMessage: String
    ): CacheResult<Nothing>()
}