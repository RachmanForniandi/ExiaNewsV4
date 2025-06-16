package rachman.forniandi.exianewsv4.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rachman.forniandi.exianewsv4.source.ArticleModel
import rachman.forniandi.exianewsv4.source.news.NewsDao
import rachman.forniandi.exianewsv4.util.SourceConverter

@Database(
    entities = [ArticleModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(SourceConverter::class)
abstract class DatabaseClient:RoomDatabase() {
    abstract val newsDao:NewsDao
}