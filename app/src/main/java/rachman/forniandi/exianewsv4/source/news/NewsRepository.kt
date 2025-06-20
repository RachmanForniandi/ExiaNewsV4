package rachman.forniandi.exianewsv4.source.news


import rachman.forniandi.exianewsv4.BuildConfig
import org.koin.dsl.module
import rachman.forniandi.exianewsv4.source.ArticleModel
import rachman.forniandi.exianewsv4.source.NewsModel
import rachman.forniandi.exianewsv4.source.network.APIClient

val repositoryModule = module {
    factory { NewsRepository(get(),get()) }
}
class NewsRepository(private val apiClient: APIClient,val db:NewsDao) {

    suspend fun fetchDataNews(
        category: String? = "",
        query: String,
        page:Int,
    ): NewsModel {
        return apiClient.getNews(
            BuildConfig.API_KEY,
            "us",
            category!!,
            query,
            page
        )
    }

    suspend fun find(articleModel:ArticleModel) = db.find(articleModel.publishedAt)

    suspend fun save(articleModel:ArticleModel) {
        db.save(articleModel)
    }

    suspend fun remove(articleModel:ArticleModel) {
        db.remove(articleModel)
    }
}