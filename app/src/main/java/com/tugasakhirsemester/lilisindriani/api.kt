package com.tugasakhirsemester.lilisindriani
import retrofit2.Call
import retrofit2.http.GET


interface api {

    //https://coronavirus-19-api.herokuapp.com/countries
    @GET("countries")
    fun getInfo(): Call<List<ayo>>
}