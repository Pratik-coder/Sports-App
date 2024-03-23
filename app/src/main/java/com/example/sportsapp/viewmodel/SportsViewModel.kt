package com.example.sportsapp.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sportsapp.application.SportsApplication
import com.example.sportsapp.models.LeagueResponse
import com.example.sportsapp.models.PlayerResponse
import com.example.sportsapp.network.Status
import com.example.sportsapp.repository.SportsRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class SportsViewModel(private val app: Application, private val repository: SportsRepository):AndroidViewModel(app) {

    val leagueList=MutableLiveData<Status<LeagueResponse>>()
    val searchPlayerList=MutableLiveData<Status<PlayerResponse>>()

    fun getLeagueList()=viewModelScope.launch{
         getAllLeagues()
    }

    fun getPlayersByFiltering(strQuery:String)=viewModelScope.launch{
         getPlayersBySearch(strQuery)
    }

    private suspend fun getAllLeagues(){
       leagueList.postValue(Status.Loading())
        try {
            if (hasInternetConnection()){
                val response=repository.getAllLeagues()
                leagueList.postValue(handleLeagueResponse(response=response))
            }
            else{
                leagueList.postValue(Status.Error(message = "Please try again"))
            }
        }catch (t:Throwable){
            when(t){
                is IOException->leagueList.postValue(Status.Error(message = "An Error Occurred"))
                else->leagueList.postValue(Status.Error(message = "UnExpected Error"))
            }
        }
    }

    private suspend fun getPlayersBySearch(strQuery:String){
        searchPlayerList.postValue(Status.Loading())
        try {
            if (hasInternetConnection()){
                val response=repository.getPlayersBySearch(strQuery)
                searchPlayerList.postValue(handlePlayerSearchResponse(response=response))
            }
            else{
                searchPlayerList.postValue(Status.Error(message = "Please try again"))
            }
        }
        catch (t:Throwable){
            when(t){
                is IOException->searchPlayerList.postValue(Status.Error(message = "An Error Occurred"))
                else->searchPlayerList.postValue(Status.Error(message = "Unexpected Error"))
            }
        }
    }



    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<SportsApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }

    private fun handleLeagueResponse(response: Response<LeagueResponse>):Status<LeagueResponse>{
        if (response.isSuccessful){
            response.body()?.let {
                return Status.Success(it)
            }
        }
        return Status.Error(message ="No Internet" )
    }

    private fun handlePlayerSearchResponse(response: Response<PlayerResponse>):Status<PlayerResponse>{
        if (response.isSuccessful){
            response.body()?.let {
                return Status.Success(it)
            }
        }
        return Status.Error(message = "No Internet")
    }
}