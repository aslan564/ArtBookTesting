package aslan.aslan.artbooktesting.roomDB.network


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