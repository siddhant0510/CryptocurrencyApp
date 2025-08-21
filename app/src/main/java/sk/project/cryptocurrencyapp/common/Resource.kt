package sk.project.cryptocurrencyapp.common

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(date: T) : Resource<T>(date)
    class Error<T>(message: String, date: T? = null) : Resource<T>(date, message)
    class Loading<T>(date: T? = null) : Resource<T>(date)
}