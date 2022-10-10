package com.knight.moonreaderdatabase.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knight.moonreaderdatabase.dataclass.Dataclass
import kotlinx.coroutines.launch

class RFViewM(private val repo: RFRepo): ViewModel() {

    val myRes: MutableLiveData<Dataclass> = MutableLiveData()
    val lolRes: MutableLiveData<Dataclass> = MutableLiveData()
    fun getPost() {
        viewModelScope.launch {
            val resp: Dataclass = repo.getPost()
            myRes.value = resp
        }
    }

    fun getBook(string: String) {
        viewModelScope.launch {
            val resp: Dataclass = repo.getBook(string)
            lolRes.value = resp
        }
    }
}