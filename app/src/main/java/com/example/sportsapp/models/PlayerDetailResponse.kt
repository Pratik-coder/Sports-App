package com.example.sportsapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PlayerDetailResponse(
    @SerializedName("players") @Expose val players:List<PlayerData>?
)
