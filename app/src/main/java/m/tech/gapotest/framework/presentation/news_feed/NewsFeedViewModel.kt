package m.tech.gapotest.framework.presentation.news_feed

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import m.tech.gapotest.business.data.DataState
import m.tech.gapotest.business.domain.NewsFeed
import m.tech.gapotest.business.interactors.GetNewsFeed
import m.tech.gapotest.util.Constants.DATA_EXHAUSTED
import m.tech.gapotest.util.Constants.DEFAULT_LIMIT

class NewsFeedViewModel
@ViewModelInject
constructor(
    @Assisted private val savedStateHandler: SavedStateHandle,
    private val getNewsFeed: GetNewsFeed
) : ViewModel() {

    private val _newsFeed = MutableLiveData<DataState<List<NewsFeed>>>()
    val newsFeed: LiveData<DataState<List<NewsFeed>>>
        get() = _newsFeed

    private var _currentPage = 1
    private var _isQuerying = false
    private var _isExhausted = false

    fun getNewsFeed(isFetch: Boolean) = viewModelScope.launch(IO) {
        getNewsFeed.getNewsFeed(isFetch = isFetch, page = _currentPage, DEFAULT_LIMIT).collect {
            setNewsFeed(it)

            if (it is DataState.Success || it is DataState.Error) {
                _isExhausted = ((it.data?.size ?: 0) % DEFAULT_LIMIT) != 0
                _isQuerying = false
                if (_isExhausted)
                    setNewsFeed(DataState.Error(DATA_EXHAUSTED, it.data))
            }
        }
    }

    private suspend fun setNewsFeed(list: DataState<List<NewsFeed>>) = withContext(Main){
        _newsFeed.value = list
    }

    fun nextPage() {
        if (_isQuerying || _isExhausted) {
            return
        }

        _currentPage++
        getNewsFeed(isFetch = false)
    }
}