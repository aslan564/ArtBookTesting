package aslan.aslan.artbooktesting.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import aslan.aslan.artbooktesting.R
import aslan.aslan.artbooktesting.db.model.entity.Art
import aslan.aslan.artbooktesting.db.model.pojo.ImageResultPOJO
import aslan.aslan.artbooktesting.db.model.pojo.ServerResponsePOJO
import aslan.aslan.artbooktesting.db.network.NetworkResult
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.ViewTarget
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response


fun ImageView.downloadGlide(url: String, onComplete: (Boolean) -> Unit) {

    Glide.with(context).setDefaultRequestOptions(
        RequestOptions().placeholder(R.drawable.ic_baseline_broken_image_24)
            .error(R.drawable.ic_baseline_broken_image_24)
    ).load(url).into(this)
    onComplete(true)
}


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

fun List<ImageResultPOJO>.convertToArtList(): List<Art> = map {
    Art(
        id = it.id.toLong(),
        userId = it.userId,
        name = it.user,
        type = it.type,
        artistName = it.user,
        year = Int.MIN_VALUE,
        previewURL = it.previewURL,
        largeImageURL = it.largeImageURL

    )
}