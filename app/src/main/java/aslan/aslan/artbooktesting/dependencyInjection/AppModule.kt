package aslan.aslan.artbooktesting.dependencyInjection

import android.content.Context
import androidx.room.Room
import aslan.aslan.artbooktesting.R
import aslan.aslan.artbooktesting.roomDB.ArtDao
import aslan.aslan.artbooktesting.roomDB.ArtDatabase
import aslan.aslan.artbooktesting.roomDB.network.service.ArtsService
import aslan.aslan.artbooktesting.repository.ImageRepositoryImpl
import aslan.aslan.artbooktesting.repository.artInterface.ImageRepository
import aslan.aslan.artbooktesting.util.NetworkConstants
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


        @Singleton
        @Provides
        fun injectRoomDatabase(
            @ApplicationContext context: Context
        ) = Room.databaseBuilder(context, ArtDatabase::class.java,"ArtBookDB").build()

        @Singleton
        @Provides
        fun injectDao(
            database: ArtDatabase
        ) = database.artDao()


        @Singleton
        @Provides
        fun injectRetrofitAPI() : ArtsService {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NetworkConstants.BASE_URL).build().create(ArtsService::class.java)
        }

        @Singleton
        @Provides
        fun injectNormalRepo(dao : ArtDao, api: ArtsService) = ImageRepositoryImpl(dao,api) as ImageRepository

        @Singleton
        @Provides
        fun injectGlide(@ApplicationContext context: Context) = Glide
            .with(context).setDefaultRequestOptions(
                RequestOptions().placeholder(R.drawable.ic_baseline_broken_image_24)
                    .error(R.drawable.ic_baseline_broken_image_24)
            )



    /*
     private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val okHttpClientBuilder = OkHttpClient.Builder()
    private var retrofit: Retrofit? = null

    private fun okHttpClient(): OkHttpClient {
        when {
            BuildConfig.DEBUG -> {

                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                okHttpClientBuilder.addInterceptor(logging)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
            }
        }
        return okHttpClientBuilder.build()
    }

    private fun getClient(): Retrofit {
        when (retrofit) {
            null -> {
                retrofit = Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .client(okHttpClient())
                    .baseUrl(NetworkConstants.BASE_URL)
                    .build()
            }
        }
        return retrofit as Retrofit
    }

    @Singleton
    @Provides
    fun getArtApi():ArtsService{
        return getClient().create(ArtsService::class.java)
    }*/

}