package com.example.sportsapp.repository

import com.example.sportsapp.api.SportsApi
import com.example.sportsapp.models.LeagueResponse
import com.example.sportsapp.models.PlayerResponse
import com.example.sportsapp.retrofit.RetrofitClient
import retrofit2.Response

class SportsRepository {

    suspend fun getAllLeagues():Response<LeagueResponse>{
        return RetrofitClient.getRetrofitInstance().create(SportsApi::class.java).getAllLeagues()
      //  return RetrofitInstance.api.getAllLeagues()
    }

    suspend fun getPlayersBySearch(strQuery:String):Response<PlayerResponse>{
        return RetrofitClient.getRetrofitInstance().create(SportsApi::class.java).getPlayersBySearch(strQuery)
       // return RetrofitInstance.api.getPlayersBySearch(strQuery)
    }
}