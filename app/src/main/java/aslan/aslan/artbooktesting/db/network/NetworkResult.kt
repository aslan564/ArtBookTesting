package aslan.aslan.artbooktesting.db.network

import java.lang.IllegalStateException
import java.lang.StringBuilder

sealed class NetworkResult {
    data class Success<T>(val result: T?) : NetworkResult()

    data class Failure(val msg: String) : NetworkResult()

    sealed class Exceptions(
        code: Int,
        msg: String,
        errors: Map<String, String>? = null
    ) : IllegalStateException(msg) {
        data class ServerError(
            val code: Int,
            val msg: String
        ) :
            Exceptions(code, msg)

        data class ApiError(
            val code: Int,
            val msg: String,
            val errors: Map<String, String>?
        ) :
            Exceptions(code, msg, errors)
    }
}
