package com.example.abuseyou.modules.list.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abuseyou.api.ApiService
import com.example.abuseyou.db.MyDatabase
import com.example.abuseyou.db.model.AbuseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val apiService: ApiService,
    database: MyDatabase
) : ViewModel() {

    private val dao = database.dao()
    val insults = MutableLiveData<List<AbuseModel>>()

    fun getInsults() {
        viewModelScope.launch {
            try {
                insults.value = dao.getAllInsults()
            } catch (e: Exception) {
                Log.d("dbbd", e.toString())
            }
        }
    }

    fun searchInsults(name: String) {
        viewModelScope.launch {
            insults.value =
                dao.searchAllInsults("%" + name + "%")
        }
    }

    fun del(id: Int) {
        viewModelScope.launch {
            dao.removeInsult(id)
            getInsults()
        }
    }

}