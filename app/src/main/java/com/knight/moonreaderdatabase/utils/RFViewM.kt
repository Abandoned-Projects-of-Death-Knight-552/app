package com.knight.moonreaderdatabase.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knight.moonreaderdatabase.dataclass.AnResp
import com.knight.moonreaderdatabase.dataclass.Dataclass
import kotlinx.coroutines.launch

class RFViewM(private val repo: RFRepo): ViewModel() {

    val lolRes: MutableLiveData<Dataclass> = MutableLiveData()
    val anilist_list: MutableLiveData<AnResp> = MutableLiveData()

    fun getBook(string: String) {
        viewModelScope.launch {
            val resp: Dataclass = repo.getBook(string)
            lolRes.value = resp
        }
    }

    fun getSearchResult(string: String) {
        viewModelScope.launch {
            val resp: AnResp = repo.getSearchRes(string)
            anilist_list.value = resp
        }
    }
}