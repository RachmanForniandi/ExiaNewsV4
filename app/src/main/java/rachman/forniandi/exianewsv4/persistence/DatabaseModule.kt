package rachman.forniandi.exianewsv4.persistence

import android.app.Application
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import rachman.forniandi.exianewsv4.source.news.NewsDao

val databaseModule =module {
    single { provideDatabase(androidApplication()) }
    single { provideNews(get()) }
}

fun provideDatabase(application: Application):DatabaseClient{
    return Room.databaseBuilder(application,DatabaseClient::class.java,"exianews.db")
        .fallbackToDestructiveMigration()
        .build()
}

fun provideNews(dbClient:DatabaseClient):NewsDao{
    return dbClient.newsDao
}