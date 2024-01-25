package com.example.openappchart.HomeViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openappchart.api.BaseUrl
import com.example.openappchart.OpenInModel.TopLinks
import com.example.openappchart.api.Service
import kotlinx.coroutines.launch


class HomeViewModel():ViewModel(){
    val topLinks: MutableLiveData<TopLinks> = MutableLiveData()

    fun getApiLink() = viewModelScope.launch {
        val service = BaseUrl.getInstance().create(Service::class.java)
        val link = service.getLink()
        topLinks.value = link.body()
    }

}