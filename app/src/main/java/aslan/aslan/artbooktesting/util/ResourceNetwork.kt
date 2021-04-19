package aslan.aslan.artbooktesting.util

data class ResourceNetwork<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): ResourceNetwork<T> {
            return ResourceNetwork(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): ResourceNetwork<T> {
            return ResourceNetwork(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): ResourceNetwork<T> {
            return ResourceNetwork(Status.LOADING, data, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    STABLE
}