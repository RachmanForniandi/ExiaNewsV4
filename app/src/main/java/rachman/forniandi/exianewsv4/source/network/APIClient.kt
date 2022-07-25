package rachman.forniandi.exianewsv4.source.network

import rachman.forniandi.exianewsv4.source.NewsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface APIClient {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("apiKey")apiKey:String,
        @Query("country")country:String,
        @Query("category")category:String,
        @Query("q")query:String,
        @Query("page")page:Int
    ):NewsModel
}