package rachman.forniandi.exianewsv4.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.dsl.module
import rachman.forniandi.exianewsv4.source.NewsModel
import rachman.forniandi.exianewsv4.source.news.CategoryModel

import rachman.forniandi.exianewsv4.source.news.NewsRepository
import java.lang.Exception

val homeViewModel = module {
    factory { HomeViewModel(get()) }
}
class HomeViewModel (
    val repository: NewsRepository
    ): ViewModel() {
    val title ="Berita"
    val category by lazy { MutableLiveData<String>() }
    val message by lazy { MutableLiveData<String>() }
    val news by lazy { MutableLiveData<NewsModel>() }
    init {
        category.value=""
        message.value =null
        fetchNewsData()
    }

    fun fetchNewsData(){
        viewModelScope.launch {
            try {
                val response =repository.fetchDataNews("","",1)
                news.value = response
            }catch (e:Exception){
                message.value="Terjadi Error data"
            }
        }
    }
    val categories = listOf<CategoryModel>(
        CategoryModel("","Berita Utama"),
        CategoryModel("business","Bisnis"),
        CategoryModel("entertainment","Hiburan"),
        CategoryModel("general","Umum"),
        CategoryModel("health","Kesehatan"),
        CategoryModel("science","Sains"),
        CategoryModel("sports","Olah Raga"),
        CategoryModel("technology","Teknologi")
    )

}