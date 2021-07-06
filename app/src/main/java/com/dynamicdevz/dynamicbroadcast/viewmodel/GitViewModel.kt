package com.dynamicdevz.dynamicbroadcast.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dynamicdevz.dynamicbroadcast.model.GitResponse
import com.dynamicdevz.dynamicbroadcast.model.GitResponseItem
import com.dynamicdevz.dynamicbroadcast.network.GitRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitViewModel: ViewModel() {

    private val gitRetrofit = GitRetrofit()

    val gitLiveData = MutableLiveData<List<GitResponseItem>>()

    fun getAllRepos() {
        gitRetrofit.getRepos().enqueue(object: Callback<GitResponse>{
            override fun onResponse(call: Call<GitResponse>, response: Response<GitResponse>) {
                response.body()?.let {
                    gitLiveData.postValue(it)
                } ?: {
//                   Handle Error and log
                }()
            }

            override fun onFailure(call: Call<GitResponse>, t: Throwable) {
                //HANDLE ERROR AND LOG IT TOO
            }
        })
    }



}