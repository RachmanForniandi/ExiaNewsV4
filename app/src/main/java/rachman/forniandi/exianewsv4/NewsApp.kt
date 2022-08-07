package rachman.forniandi.exianewsv4

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import rachman.forniandi.exianewsv4.persistence.databaseModule
import rachman.forniandi.exianewsv4.source.network.networkModule
import rachman.forniandi.exianewsv4.source.news.repositoryModule
import rachman.forniandi.exianewsv4.ui.bookmark.bookMarkModule
import rachman.forniandi.exianewsv4.ui.bookmark.bookmarkViewModel
import rachman.forniandi.exianewsv4.ui.detail.detailModule
import rachman.forniandi.exianewsv4.ui.detail.detailViewModel
import rachman.forniandi.exianewsv4.ui.home.homeModule
import rachman.forniandi.exianewsv4.ui.home.homeViewModel
import timber.log.Timber

class NewsApp:Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        //Timber.e("run base application")
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            if (BuildConfig.DEBUG)
            androidLogger()
            androidContext(this@NewsApp)
            modules(
                networkModule,
                repositoryModule,
                homeViewModel,
                homeModule,
                bookmarkViewModel,
                bookMarkModule,
                databaseModule,
                detailViewModel,
                detailModule
            )
        }
    }
}