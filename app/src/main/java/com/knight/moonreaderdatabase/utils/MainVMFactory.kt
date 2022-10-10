package com.knight.moonreaderdatabase.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.knight.moonreaderdatabase.utils.RFRepo

class MainVMFactory(private val repo: RFRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RFViewM(repo) as T
    }
}