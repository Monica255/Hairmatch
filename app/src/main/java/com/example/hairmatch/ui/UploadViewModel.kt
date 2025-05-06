package com.example.hairmatch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hairmatch.core.data.Resource
import com.example.hairmatch.core.data.remote.model.Response
import com.example.hairmatch.core.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    private val _uploadLiveData = MutableLiveData<Resource<Response>>()
    val uploadLiveData: LiveData<Resource<Response>> = _uploadLiveData

    fun upload(data: MultipartBody.Part) {
        viewModelScope.launch {
            repository.upload(data).collect { data ->
                when (data) {
                    is Resource.Loading -> _uploadLiveData.postValue(Resource.Loading())
                    is Resource.Success -> {
                        data.data?.let {
                            _uploadLiveData.postValue(Resource.Success(it))
                        } ?: _uploadLiveData.postValue(Resource.Error("Error", data.data))
                    }

                    is Resource.Error -> _uploadLiveData.postValue(
                        data.message?.let {
                            Resource.Error(it)
                        })
                }
            }
        }
    }
}