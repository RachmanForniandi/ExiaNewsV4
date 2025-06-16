package rachman.forniandi.exianewsv4.ui.bookmark

import androidx.lifecycle.ViewModel
import org.koin.dsl.module
import rachman.forniandi.exianewsv4.source.news.NewsRepository

val bookmarkViewModel= module {
    factory { BookmarkViewModel(get()) }
}
class BookmarkViewModel(repository: NewsRepository):ViewModel(

) {
    val title="Saved"
    val articles = repository.db.findAll()
}