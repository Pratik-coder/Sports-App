package com.example.sportsapp.api

import com.example.sportsapp.models.LeagueResponse
import com.example.sportsapp.models.PlayerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SportsApi {

    @GET("/api/v1/json/3/all_leagues.php")
    suspend fun getAllLeagues():Response<LeagueResponse>

    @GET("/api/v1/json/3/searchplayers.php")
    suspend fun getPlayersBySearch(@Query("p")query:String):Response<PlayerResponse>
}