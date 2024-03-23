package com.example.sportsapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LeagueResponse(
    @SerializedName("leagues") @Expose val leagues:List<LeagueData>)

data class LeagueData(
@SerializedName("idLeague") @Expose val idLeague:String,
@SerializedName("strLeague") @Expose val strLeague:String,
@SerializedName("strSport") @Expose val strSport:String,
@SerializedName("strLeagueAlternate") @Expose val strLeagueAlternate:String,
)
