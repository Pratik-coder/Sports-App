package com.example.sportsapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PlayerResponse(
    @SerializedName("player") @Expose val player:List<PlayerData>?
)

data class PlayerData(
    @SerializedName("idPlayer") @Expose val idPlayer:String,
    @SerializedName("idTeam") @Expose val idTeam:String?,
    @SerializedName("idTeam2")@Expose val idTeam2:String?,
    @SerializedName("idTeamNational")@Expose val idTeamNational:String?,
    @SerializedName("idSoccerXML")@Expose val idSoccerXML:String?,
    @SerializedName("idAPIfootball")@Expose val idAPIfootball:String?,
    @SerializedName("idPlayerManager")@Expose val idPlayerManager:String?,
    @SerializedName("idWikidata")@Expose val idWikidata:String?,
    @SerializedName("strNationality")@Expose val strNationality:String?,
    @SerializedName("strPlayer")@Expose val strPlayer:String?,
    @SerializedName("strPlayerAlternate")@Expose val strPlayerAlternate:String?,
    @SerializedName("strTeam")@Expose val strTeam:String?,
    @SerializedName("strTeam2")@Expose val strTeam2:String?,
    @SerializedName("strSport")@Expose val strSport:String?,
    @SerializedName("intSoccerXMLTeamID")@Expose val intSoccerXMLTeamID:String?,
    @SerializedName("dateBorn")@Expose val dateBorn:String?,
    @SerializedName("strNumber")@Expose val strNumber:String?,
    @SerializedName("dateSigned")@Expose val dateSigned:String?,
    @SerializedName("strSigning")@Expose val strSigning:String?,
    @SerializedName("strWage")@Expose val strWage:String?,
    @SerializedName("strOutfitter")@Expose val strOutfitter:String?,
    @SerializedName("strKit")@Expose val strKit:String?,
    @SerializedName("strAgent")@Expose val strAgent:String?,
    @SerializedName("strBirthLocation")@Expose val strBirthLocation:String?,
    @SerializedName("strEthnicity")@Expose val strEthnicity:String?,
    @SerializedName("strStatus")@Expose val strStatus:String?,
    @SerializedName("strDescriptionEN")@Expose val strDescriptionEN:String?,
    @SerializedName("strDescriptionDE")@Expose val strDescriptionDE:String?,
    @SerializedName("strDescriptionFR")@Expose val strDescriptionFR:String?,
    @SerializedName("strDescriptionCN")@Expose val strDescriptionCN:String?,
    @SerializedName("strDescriptionIT")@Expose val strDescriptionIT:String?,
    @SerializedName("strDescriptionJP")@Expose val strDescriptionJP:String?,
    @SerializedName("strDescriptionRU")@Expose val strDescriptionRU:String?,
    @SerializedName("strDescriptionES")@Expose val strDescriptionES:String?,
    @SerializedName("strDescriptionPT")@Expose val strDescriptionPT:String?,
    @SerializedName("strDescriptionSE")@Expose val strDescriptionSE:String?,
    @SerializedName("strDescriptionNL")@Expose val strDescriptionNL:String?,
    @SerializedName("strDescriptionHU")@Expose val strDescriptionHU:String?,
    @SerializedName("strDescriptionNO")@Expose val strDescriptionNO:String?,
    @SerializedName("strDescriptionIL")@Expose val strDescriptionIL:String?,
    @SerializedName("strDescriptionPL")@Expose val strDescriptionPL:String?,
    @SerializedName("strGender")@Expose val strGender:String?,
    @SerializedName("strSide")@Expose val strSide:String?,
    @SerializedName("strPosition")@Expose val strPosition:String?,
    @SerializedName("strCollege")@Expose val strCollege:String?,
    @SerializedName("strFacebook")@Expose val strFacebook:String?,
    @SerializedName("strWebsite")@Expose val strWebsite:String?,
    @SerializedName("strTwitter")@Expose val strTwitter:String?,
    @SerializedName("strInstagram")@Expose val strInstagram:String?,
    @SerializedName("strYoutube")@Expose val strYoutube:String?,
    @SerializedName("strHeight")@Expose val strHeight:String?,
    @SerializedName("strWeight")@Expose val strWeight:String?,
    @SerializedName("intLoved")@Expose val intLoved:String?,
    @SerializedName("strThumb")@Expose val strThumb:String?=null,
    @SerializedName("strCutout")@Expose val strCutout:String?,
    @SerializedName("strRender")@Expose val strRender:String?,
    @SerializedName("strBanner")@Expose val strBanner:String?,
    @SerializedName("strFanart1")@Expose val strFanart1:String?,
    @SerializedName("strFanart2")@Expose val strFanart2:String?,
    @SerializedName("strFanart3")@Expose val strFanart3:String?,
    @SerializedName("strFanart4")@Expose val strFanart4:String?,
    @SerializedName("strCreativeCommons")@Expose val strCreativeCommons:String?,
    @SerializedName("strLocked")@Expose val strLocked:String?,
)
