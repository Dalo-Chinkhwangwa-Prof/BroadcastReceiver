package com.dynamicdevz.dynamicbroadcast.network

import com.dynamicdevz.dynamicbroadcast.model.GitResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class GitRetrofit {
    private val gitService = createRetrofit().create(GitService::class.java)
    private fun createRetrofit() = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getRepos() = gitService.getAllRepositories()

    interface GitService{
        @GET("users/Dalo-Chinkhwangwa-Prof/repos")
        fun getAllRepositories(): Call<GitResponse>
    }
}