package rachman.forniandi.exianewsv4.ui.bookmark

import androidx.lifecycle.ViewModel
import org.koin.dsl.module
import rachman.forniandi.exianewsv4.source.NewsRepository

val bookmarkViewModel= module {
    factory { BookmarkViewModel(get()) }
}
class BookmarkViewModel(val repository: NewsRepository):ViewModel(

) {
    val title="Disimpan"
}