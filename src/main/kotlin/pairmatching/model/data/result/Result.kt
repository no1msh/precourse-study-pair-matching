package pairmatching.model.data.result

sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val exception: Exception = Exception()): Result<Nothing>()
}