package com.test.a4.ui.adapters

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.squareup.picasso.Picasso
import com.test.a4.data.network.response.FixturesResponse
import com.test.a4.data.network.response.ResultsResponse
import com.test.a4.data.network.response.SquadResponse
import com.test.a4.data.network.response.TableInfoResponse
import com.test.a4.databinding.FixturesItemBinding
import com.test.a4.databinding.ResultItemBinding
import com.test.a4.databinding.SquadItemBinding
import com.test.a4.databinding.TableItemBinding
import com.test.a4.databinding.TableItemTitleBinding
import com.test.a4.ui.adapters.entity.LeagueTitle

object Delegate {

     var fixturesAdapter = adapterDelegateViewBinding<FixturesResponse,Delegates,FixturesItemBinding>({
        inflater,container -> FixturesItemBinding.inflate(inflater,container,false)
    }){
        bind {
            if(item.image.isNotEmpty())
            Picasso.get().load(item.image).into(binding.imMatch)

            binding.tvCompetition.text = item.competition
            binding.tvDate.text = item.date
            binding.tvTime.text = item.time
            binding.tvMatch.text = item.match
        }
    }

    var resultAdapter = adapterDelegateViewBinding<ResultsResponse,Delegates,ResultItemBinding>({
            inflater,container -> ResultItemBinding.inflate(inflater,container,false)
    }){
        bind{
            if(item.image.isNotEmpty())
                Picasso.get().load(item.image).into(binding.imMatchResult)

            binding.tvDate.text = item.date
            binding.tvCompetitionResult.text = item.competition
            binding.tvCount.text = item.count
            binding.tvMatchResult.text = item.match
        }
    }

    var squadAdapter = adapterDelegateViewBinding<SquadResponse,Delegates,SquadItemBinding>({
            inflater,container -> SquadItemBinding.inflate(inflater,container,false)
    }){
        bind {
            if(item.image.isNotEmpty())
                Picasso.get().load(item.image).into(binding.imPlayer)

            binding.tvAge.text = item.age
            binding.tvCountry.text = item.nat
            binding.tvHeight.text = item.ht
            binding.tvWeight.text = item.wt
            binding.tvPlayerName.text = item.name
            binding.tvPlayerPosition.text = item.position
        }
    }

    var tableTitleAdapter = adapterDelegateViewBinding<LeagueTitle,Delegates,TableItemTitleBinding>({
            inflater,container -> TableItemTitleBinding.inflate(inflater,container,false)
    }){}

    var tableAdapter = adapterDelegateViewBinding<TableInfoResponse,Delegates,TableItemBinding>({
            inflater,container -> TableItemBinding.inflate(inflater,container,false)
    }){
        bind{
            binding.tvNumber.text = position.toString()
            binding.tvD.text = item.d
            binding.tvCommand.text = item.team
            binding.tvL.text = item.l
            binding.tvGd.text = item.gd
            binding.tvGp.text = item.gp
            binding.tvP.text = item.p
            binding.tvW.text = item.w
        }
    }


}