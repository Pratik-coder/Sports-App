package com.example.sportsapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sportsapp.R
import com.example.sportsapp.databinding.ActivityHomeBinding
import com.example.sportsapp.repository.SportsRepository
import com.example.sportsapp.viewmodel.SportsViewModel
import com.example.sportsapp.viewmodelfactory.SportsViewModelFactory
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    lateinit var viewModel:SportsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository=SportsRepository()
        val viewModelFactory=SportsViewModelFactory(application,repository)
        viewModel= ViewModelProvider(this,viewModelFactory)[SportsViewModel::class.java]
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNavigationView.setupWithNavController(sportsHostFragment.findNavController())
    }
}