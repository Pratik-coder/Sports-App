package com.example.sportsapp.fragments

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportsapp.R
import com.example.sportsapp.activity.HomeActivity
import com.example.sportsapp.adapter.LeagueAdapter
import com.example.sportsapp.adapter.PlayerAdapter
import com.example.sportsapp.constant.Constants
import com.example.sportsapp.databinding.FragmentSearchBinding
import com.example.sportsapp.network.Status
import com.example.sportsapp.viewmodel.SportsViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var sportsViewModel: SportsViewModel
    private lateinit var binding: FragmentSearchBinding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var playerAdapter: PlayerAdapter
    private var job: Job? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        progressDialog = ProgressDialog(requireContext())
        sportsViewModel = (activity as HomeActivity).viewModel


        setUpRecyclerView()
        playerAdapter.setOnItemClickListener {
            val bundle=Bundle().apply {
                putSerializable("playerId",it.idPlayer)
            }
            findNavController().navigate(R.id.action_searchfragment_to_detailfragment,bundle)
        }

        binding.etSearch.addTextChangedListener {
            job?.cancel()
            job = MainScope().launch {
                delay(Constants.SEARCH_DELAY)
                it?.let {
                    if (it.toString().isNotEmpty()) {
                        binding.searchImage.visibility = View.INVISIBLE
                        binding.rvSearchLeague.visibility = View.VISIBLE
                        sportsViewModel.getPlayersByFiltering(it.toString())
                    }
                    else{
                        binding.searchImage.visibility = View.VISIBLE
                        binding.rvSearchLeague.visibility = View.GONE
                    }
                }
            }
        }

        sportsViewModel.searchPlayerList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Status.Loading -> {
                    progressDialog.setMessage("Loading...")
                    progressDialog.setCancelable(false)
                    progressDialog.show()
                }

                is Status.Success -> {
                    progressDialog.dismiss()
                    result.data?.let {
                        playerAdapter.differ.submitList(it.player?.toList())
                    }
                }

                is Status.Error -> {
                    progressDialog.dismiss()
                    result.message?.let {
                        Toast.makeText(requireContext(), "An Error Occurred", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        })
        return binding.root

    }

    private fun setUpRecyclerView() {
        playerAdapter = PlayerAdapter()
        binding.rvSearchLeague.apply {
            adapter = playerAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}