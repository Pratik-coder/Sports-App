package com.example.sportsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sportsapp.R
import com.example.sportsapp.databinding.LayoutPlayerlistBinding
import com.example.sportsapp.models.LeagueData
import com.example.sportsapp.models.PlayerData

class PlayerAdapter:RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view=LayoutPlayerlistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
          holder.onBind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder(private val binding: LayoutPlayerlistBinding):RecyclerView.ViewHolder(binding.root) {
        private val textViewPlayerName:TextView=binding.textViewPlayerName
        private val textViewNationality:TextView=binding.textViewNationality
        private val textViewHeight:TextView=binding.textViewHeight
        private val cardView:CardView=binding.cardView


        fun onBind(playerData: PlayerData){
            val playerImageUrl=playerData.strThumb
            Glide.with(binding.root).load(playerImageUrl).error(R.drawable.default_image).into(binding.imageViewPlayer)
            textViewPlayerName.text=playerData.strPlayer
            textViewNationality.text=playerData.strNationality
            textViewHeight.text=playerData.strHeight
            cardView.setOnClickListener {
                onItemClickListener?.let {
                   it(playerData)
                }
            }

        }
     }

    private var onItemClickListener:((PlayerData)->Unit)?=null

    fun setOnItemClickListener(listener:(PlayerData)->Unit){
        onItemClickListener=listener
    }

    private val differCallBack=object : DiffUtil.ItemCallback<PlayerData>(){
        override fun areItemsTheSame(oldItem: PlayerData, newItem: PlayerData): Boolean {
            return oldItem.idPlayer==newItem.idPlayer
        }

        override fun areContentsTheSame(oldItem: PlayerData, newItem: PlayerData): Boolean {
            return oldItem==newItem
        }
    }

    val differ= AsyncListDiffer(this,differCallBack)
}