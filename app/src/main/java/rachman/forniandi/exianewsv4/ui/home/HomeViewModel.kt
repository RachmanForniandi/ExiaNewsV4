package rachman.forniandi.exianewsv4.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.dsl.module
import rachman.forniandi.exianewsv4.source.NewsModel
import rachman.forniandi.exianewsv4.source.news.CategoryModel

import rachman.forniandi.exianewsv4.source.news.NewsRepository
import timber.log.Timber
import java.lang.Exception
import kotlin.math.ceil

val homeViewModel = module {
    factory { HomeViewModel(get()) }
}
class HomeViewModel (
    val repository: NewsRepository
    ): ViewModel() {
    val title ="Berita"
    val category by lazy { MutableLiveData<String>() }
    val message by lazy { MutableLiveData<String>() }
    val loading by lazy { MutableLiveData<Boolean>() }
    val loadMore by lazy { MutableLiveData<Boolean>() }
    val news by lazy { MutableLiveData<NewsModel>() }
    init {
        category.value=""
        message.value =null
        //fetchNewsData()
    }

    var query =""
    var page=1
    var total =1

    fun fetchNewsData(){
        //loading.value = true
        Timber.e("fetchPage: $page")
        if (page >1)loadMore.value = true else loading.value = true
        viewModelScope.launch {
            try {
                val response =repository.fetchDataNews(category.value!!,query,page)
                news.value = response
                val totalResults:Double = response.totalResults /20.0
                total = ceil(totalResults).toInt()
                page++
                loading.value = false
                loadMore.value = false
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