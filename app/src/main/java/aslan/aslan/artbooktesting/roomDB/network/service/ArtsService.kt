package aslan.aslan.artbooktesting.roomDB.network.service

import aslan.aslan.artbooktesting.roomDB.model.pojo.ImageResponsePOJO
import aslan.aslan.artbooktesting.util.NetworkConstants.USER_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtsService {

    @GET("/api/")
    suspend fun getAllArts(
        @Query("key") apiKey: String=USER_KEY
    ): Response<ImageResponsePOJO>


    @GET("/api/")
    suspend fun getSearchArts(
        @Query("q")searchImage:String,
        @Query("key") apiKey: String=USER_KEY
    ): Response<ImageResponsePOJO>
}