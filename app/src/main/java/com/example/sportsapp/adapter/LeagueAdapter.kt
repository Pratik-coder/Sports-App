package com.example.sportsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsapp.R
import com.example.sportsapp.databinding.ActivityHomeBinding
import com.example.sportsapp.databinding.LayoutLeaguelistBinding
import com.example.sportsapp.models.LeagueData

class LeagueAdapter:RecyclerView.Adapter<LeagueAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view=LayoutLeaguelistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }



    class ViewHolder(private val binding: LayoutLeaguelistBinding):RecyclerView.ViewHolder(binding.root) {

        private val textViewLeague:TextView=binding.textViewLeague
        private val textViewSport:TextView=binding.textViewSport
        private val textViewLeagueAlternate:TextView=binding.textViewLeagueAlternate
        private val imageViewSport:ImageView=binding.imageViewSport

        fun onBind(leagueData: LeagueData){
            textViewLeague.text=leagueData.strLeague
            textViewSport.text=leagueData.strSport
            textViewLeagueAlternate.text=leagueData.strLeagueAlternate

            if (leagueData.strSport.equals("Soccer") || leagueData.strSport.equals("American Football")){
                binding.imageViewSport.setImageResource(R.drawable.soccer)
            }
            else if (leagueData.strSport.equals("Motorsport"))
            {
                binding.imageViewSport.setImageResource(R.drawable.motorsport)
            }
            else if(leagueData.strSport.equals("Basketball"))
            {
                binding.imageViewSport.setImageResource(R.drawable.basketball)
            }
            else{
                binding.imageViewSport.setImageResource(R.drawable.ice_hockey)
            }
        }

    }

    private val differCallBack=object : DiffUtil.ItemCallback<LeagueData>(){
        override fun areItemsTheSame(oldItem: LeagueData, newItem: LeagueData): Boolean {
            return oldItem.idLeague==newItem.idLeague
        }

        override fun areContentsTheSame(oldItem: LeagueData, newItem: LeagueData): Boolean {
            return oldItem==newItem
        }
    }

    val differ= AsyncListDiffer(this,differCallBack)

}