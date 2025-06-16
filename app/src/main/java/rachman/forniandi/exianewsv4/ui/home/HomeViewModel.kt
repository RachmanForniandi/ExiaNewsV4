package rachman.forniandi.exianewsv4.ui.home

import android.provider.Settings.Global.getString
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
    val title ="ExiaNewsV4"
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
                message.value=e.message
            }
        }
    }
    val categories = listOf<CategoryModel>(
        CategoryModel("","Headlines"),
        CategoryModel("business","Business"),
        CategoryModel("entertainment","Entertainment"),
        CategoryModel("general","General"),
        CategoryModel("health","Health"),
        CategoryModel("science","Science"),
        CategoryModel("sports","Sports"),
        CategoryModel("technology","Technology")
    )

}