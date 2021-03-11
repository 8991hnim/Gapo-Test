package m.tech.gapotest.framework.presentation.detail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import m.tech.gapotest.business.data.DataState
import m.tech.gapotest.business.domain.DetailDocument
import m.tech.gapotest.business.interactors.GetDetail

class DetailViewModel
@ViewModelInject
constructor(
    @Assisted private val savedStateHandler: SavedStateHandle,
    private val getDetail: GetDetail
) : ViewModel() {

    private val _detailDoc = MutableLiveData<DataState<DetailDocument>>()
    val detailDoc: LiveData<DataState<DetailDocument>>
        get() = _detailDoc

    fun getDetail(isFetch: Boolean, id: String) = viewModelScope.launch(IO) {
        getDetail.getDetail(isFetch, id).collect { dataState ->
            withContext(Main) {
                _detailDoc.value = dataState
            }
        }
    }


}