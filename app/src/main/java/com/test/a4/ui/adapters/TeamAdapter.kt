package com.test.a4.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.a4.data.network.response.GeneralTeamResponse
import com.test.a4.databinding.TeamItemBinding

class TeamAdapter(
    private val inflater: LayoutInflater,
    private val itemClickListener: (item: String) -> Unit,
    ): RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    private var items = mutableListOf<GeneralTeamResponse>()

    fun updateList(newList: List<GeneralTeamResponse>) {
        items.clear()
        items.addAll(newList)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(TeamItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(items[position],itemClickListener)
    }

    override fun getItemCount(): Int = items.size


    class TeamViewHolder(private val binding: TeamItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(teamResponse: GeneralTeamResponse, itemClickListener: (item: String) -> Unit){
            if(teamResponse.team_image.isNotEmpty())
                Picasso.get().load(teamResponse.team_image).into(binding.imageView)
            binding.tvTeamName.text = teamResponse.team_name

            binding.teamContainer.setOnClickListener {
             itemClickListener.invoke(teamResponse.team_name)
            }
        }
    }
}
