package com.example.abuseyou.modules.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abuseyou.api.ApiService
import com.example.abuseyou.api.model.InsultModel
import com.example.abuseyou.db.MyDatabase
import com.example.abuseyou.db.model.AbuseModel
import com.example.abuseyou.mappers.InsultMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiService: ApiService,
    database: MyDatabase
) : ViewModel() {

    private val dao = database.dao()
    val insult = MutableLiveData<AbuseModel>()

    fun getInsult() {
        viewModelScope.launch {
            insult.value = try {
                InsultMapper.apiToDbInsultModel(apiService.getInsult("ru", "json"))
            } catch (e: Exception) {
                Log.d("dbbd", e.toString())
                InsultMapper.apiToDbInsultModel(
                    InsultModel(
                        "",
                        "",
                        "Rock And Stone!!!",
                        "",
                        "",
                        "",
                        "",
                        ""
                    )
                )
            }
        }
    }

    fun putInsult(model: AbuseModel) {
        viewModelScope.launch {
            if (dao.searchAllInsults(model.insult).isEmpty()) {
                dao.insertInsult(model)
            }
        }
    }
}