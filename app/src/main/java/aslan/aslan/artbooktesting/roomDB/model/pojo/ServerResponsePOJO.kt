package aslan.aslan.artbooktesting.roomDB.model.pojo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServerResponsePOJO<T>(
    @Json(name = "errors") val errors: Map<String, String>? = null,
    @Json(name = "response") val response: T? = null,
    @Json(name = "status") val status: StatusPOJO
)

@JsonClass(generateAdapter = true)
data class StatusPOJO(
    @Json(name = "code") val code: Int,
    @Json(name = "succeeded") val succeeded: Boolean,
    @Json(name = "text") val text: String
)