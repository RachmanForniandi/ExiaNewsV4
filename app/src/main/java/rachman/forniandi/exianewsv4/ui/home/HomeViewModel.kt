package rachman.forniandi.exianewsv4.ui.home

import androidx.lifecycle.ViewModel
import org.koin.dsl.module
import rachman.forniandi.exianewsv4.source.news.CategoryModel

import rachman.forniandi.exianewsv4.source.news.NewsRepository

val homeViewModel = module {
    factory { HomeViewModel(get()) }
}
class HomeViewModel (
    val repository: NewsRepository
    ): ViewModel() {
    val title ="Berita"

    val categories = listOf<CategoryModel>(
        CategoryModel("","Berita Utama"),
        CategoryModel("business","Bisnis"),
        CategoryModel("entertainment","Hiburan"),
        CategoryModel("general","Umum"),
        CategoryModel("health","Kesehatan"),
        CategoryModel("science","Sains"),
        CategoryModel("sports","Olah Raga"),
        CategoryModel("technology","Teknologi"),
    )

}