package com.example.sportsapp.fragments

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportsapp.R
import com.example.sportsapp.activity.HomeActivity
import com.example.sportsapp.adapter.LeagueAdapter
import com.example.sportsapp.databinding.FragmentLeagueBinding
import com.example.sportsapp.network.Status
import com.example.sportsapp.viewmodel.SportsViewModel


class LeagueFragment : Fragment(R.layout.fragment_league) {

    private lateinit var sportsViewModel: SportsViewModel
    private lateinit var binding:FragmentLeagueBinding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var leagueAdapter: LeagueAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_league,container,false)
        progressDialog=ProgressDialog(requireContext())
        sportsViewModel=(activity as HomeActivity).viewModel
        sportsViewModel.getLeagueList()

        setUpRecyclerView()

        sportsViewModel.leagueList.observe(viewLifecycleOwner, Observer {
                result->
            when(result){
                is Status.Loading->{
                    progressDialog.setMessage("Loading...")
                    progressDialog.setCancelable(false)
                    progressDialog.show()
                }

                is Status.Success->{
                    progressDialog.dismiss()
                    result.data?.let {
                        leagueAdapter.differ.submitList(it.leagues.toList())
                    }
                }

                is Status.Error->{
                    progressDialog.dismiss()
                    result.message?.let {
                        Toast.makeText(requireContext(),"An Error Occurred", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        return binding.root
    }

    private fun setUpRecyclerView(){
        leagueAdapter= LeagueAdapter()
        binding.rvLeagueList.apply {
            adapter=leagueAdapter
            layoutManager=LinearLayoutManager(requireContext())
        }
    }
}