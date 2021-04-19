package aslan.aslan.artbooktesting.util

import aslan.aslan.artbooktesting.db.model.entity.Art
import aslan.aslan.artbooktesting.db.model.pojo.ImageResultPOJO
import aslan.aslan.artbooktesting.db.model.pojo.ServerResponsePOJO
import aslan.aslan.artbooktesting.db.network.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/*
@Inject
@BindingAdapter("downloadImage")
fun  ImageView.downloadGlide(url:String?,requestManager:RequestManager){
    url?.let {
        requestManager.load(url).into(this)
    }
}
*/

suspend fun <T> networkRequest(request: suspend () -> Response<ServerResponsePOJO<T>>): NetworkResult.Success<T> =
    withContext(Dispatchers.IO) {
        val response = request()

        if (response.isSuccessful && response.code() == 200) {

            if (response.body() != null) {

                return@withContext NetworkResult.Success(
                    response.body()!!.response
                )

            } else throw NetworkResult.Exceptions.ServerError(response.code(), "Null Response Body")
        } else throw NetworkResult.Exceptions.ServerError(response.code(), "Null Response Body")
    }

fun List<ImageResultPOJO>.convertToArtList():List<Art> = map {
    Art(
        id = it.id.toLong(),
        userId = it.userId,
        name = it.user,
        type = it.type,
        artistName = it.user,
        year = Int.MIN_VALUE

    )
}