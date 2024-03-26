package com.example.sportsapp.fragments

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.sportsapp.R
import com.example.sportsapp.activity.HomeActivity
import com.example.sportsapp.databinding.FragmentPlayerDetailsBinding
import com.example.sportsapp.models.PlayerData
import com.example.sportsapp.network.Status
import com.example.sportsapp.viewmodel.SportsViewModel

class PlayerDetailsFragment : Fragment() {

    private lateinit var sportsViewModel: SportsViewModel
    private lateinit var binding: FragmentPlayerDetailsBinding
    private lateinit var progressDialog: ProgressDialog
    private val arguments:PlayerDetailsFragmentArgs by navArgs()



    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_player_details,container,false)
        progressDialog=ProgressDialog(requireContext())
        sportsViewModel=(activity as HomeActivity).viewModel

        val id=arguments.playerId
        sportsViewModel.getPlayerDetails(id)

        sportsViewModel.playerDetails.observe(viewLifecycleOwner, Observer {
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
                        val playerData=it.players?.get(0)
                            setPlayerDetails(playerData)
                    }
                }

                is Status.Error->{
                    progressDialog.dismiss()
                    result.message?.let {
                        Toast.makeText(requireContext(),"An Error Occurred", Toast.LENGTH_SHORT).show()
                    }
                }

                else->{}
            }
        })

        return binding.root
    }

    private fun setPlayerDetails(playerData: PlayerData?){
            Glide.with(binding.root).load(playerData?.strThumb).error(R.drawable.default_image).into(binding.imageViewPlayer)
            binding.textViewPlayerName.text = playerData?.strPlayer
            binding.textViewPlayerCountry.text = playerData?.strNationality

            if (playerData?.strDescriptionEN.isNullOrEmpty() || playerData?.strBirthLocation.isNullOrEmpty()){
                binding.textViewBirthTitle.visibility=View.GONE
                binding.textViewDescriptionTitle.visibility=View.GONE
            }
        else {
                binding.textViewDescriptionTitle.visibility=View.VISIBLE
                binding.textViewBirthTitle.visibility=View.VISIBLE
                binding.textViewPlayerDescription.text = playerData?.strDescriptionEN
                binding.textViewPlayerBirthPlace.text=playerData?.strBirthLocation
        }

    }
}