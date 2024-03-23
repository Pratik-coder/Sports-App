package com.example.sportsapp.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sportsapp.application.SportsApplication
import com.example.sportsapp.repository.SportsRepository
import com.example.sportsapp.viewmodel.SportsViewModel
import javax.inject.Inject

class SportsViewModelFactory @Inject constructor(private val app: Application, private val repository: SportsRepository):
    ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SportsViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return SportsViewModel(app,repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}