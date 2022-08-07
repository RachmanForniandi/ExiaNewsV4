package rachman.forniandi.exianewsv4.source.news

import androidx.lifecycle.LiveData
import androidx.room.*
import rachman.forniandi.exianewsv4.source.ArticleModel

@Dao
interface NewsDao {
    @Query("SELECT * FROM tableArticle")
    fun findAll():LiveData<List<ArticleModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun save(articleModel: ArticleModel)

    @Query("SELECT COUNT(*) FROM tableArticle WHERE publishedAt=:publish")
    suspend fun find(publish:String):Int

    @Delete
    suspend fun remove(articleModel: ArticleModel)
}