package rachman.forniandi.exianewsv4.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.dsl.module
import rachman.forniandi.exianewsv4.source.ArticleModel
import rachman.forniandi.exianewsv4.source.news.NewsRepository
import java.nio.file.Files.find

val detailViewModel = module {
    factory { DetailViewModel(get()) }
}
class DetailViewModel (private val repository:NewsRepository):ViewModel(){
    val isBookmark by lazy { MutableLiveData<Int>(0) }

    fun find(articleModel: ArticleModel){
        viewModelScope.launch {
            isBookmark.value = repository.find(articleModel)
        }
    }

    fun bookmark(articleModel: ArticleModel){
        viewModelScope.launch {
            if (isBookmark.value==0)repository.save(articleModel)
            else repository.remove(articleModel)
            find(articleModel)
        }
    }
}