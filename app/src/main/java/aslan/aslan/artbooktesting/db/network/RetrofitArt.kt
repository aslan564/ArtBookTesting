package aslan.aslan.artbooktesting.db.network

import aslan.aslan.artbooktesting.BuildConfig
import aslan.aslan.artbooktesting.db.network.service.ArtsService
import aslan.aslan.artbooktesting.util.NetworkConstants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


object RetrofitArt {
/*

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val okHttpClientBuilder = OkHttpClient.Builder()
    private var retrofit: Retrofit? = null

    private fun okHttpClient():OkHttpClient{
        when{
            BuildConfig.DEBUG->{

                val logging=HttpLoggingInterceptor()
                logging.level=HttpLoggingInterceptor.Level.BODY
                okHttpClientBuilder.addInterceptor(logging)
                    .connectTimeout(10,TimeUnit.SECONDS)
                    .readTimeout(10,TimeUnit.SECONDS)
                    .writeTimeout(10,TimeUnit.SECONDS)
            }
        }
        return okHttpClientBuilder.build()
    }

    private fun getClient():Retrofit{
        when(retrofit){
            null->{
                retrofit=Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .client(okHttpClient())
                    .baseUrl(NetworkConstants.BASE_URL)
                    .build()
            }
        }
        return retrofit as Retrofit
    }

    val artService:ArtsService by lazy { getClient().create(ArtsService::class.java) }
*/


}