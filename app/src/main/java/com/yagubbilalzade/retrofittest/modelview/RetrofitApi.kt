package com.yagubbilalzade.retrofittest.modelview

import com.yagubbilalzade.retrofittest.model.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET

interface RetrofitApi {
    @GET("fetch_users.php")
    fun fetchUsers(): Call<List<Users>>
}