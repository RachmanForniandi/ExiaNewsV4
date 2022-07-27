package rachman.forniandi.exianewsv4.ui.home

import androidx.lifecycle.ViewModel
import org.koin.dsl.module
import rachman.forniandi.exianewsv4.source.NewsRepository

val homeViewModel = module {
    factory { HomeViewModel(get()) }
}
class HomeViewModel (val repository:NewsRepository): ViewModel() {
    val title ="Berita"

}