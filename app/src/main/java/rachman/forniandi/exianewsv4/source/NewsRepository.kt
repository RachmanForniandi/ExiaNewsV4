package rachman.forniandi.exianewsv4.source

import org.koin.dsl.module
import rachman.forniandi.exianewsv4.BuildConfig
import rachman.forniandi.exianewsv4.source.network.APIClient
import retrofit2.http.Query

val repositoryModule = module {
    factory { NewsRepository(get()) }
}
class NewsRepository(private val apiClient: APIClient) {

    suspend fun fetchDataNews(
        category:String,
        query: String,
        page:Int,
    ):NewsModel{
        return apiClient.getNews(
            BuildConfig.API_KEY,
            "id",
            category,
            query,
            page
        )
    }
}