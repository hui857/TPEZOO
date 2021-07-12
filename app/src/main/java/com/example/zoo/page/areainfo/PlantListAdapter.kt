package com.example.zoo.page.areainfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zoo.R
import com.example.zoo.databinding.ItemPlantBinding
import com.example.zoo.model.remote.data.PlantInfo
import com.example.zoo.page.OnItemClickListener
import com.example.zoo.utils.loadRoundRecImage

class PlantListAdapter(
    private val plantInfoList: MutableList<PlantInfo>, private  val onItemClickListener: OnItemClickListener<PlantInfo>
) : RecyclerView.Adapter<PlantListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemPlantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(plantInfoList[position])
    }

    override fun getItemCount(): Int = plantInfoList.size

    fun setData(plantInfoList: List<PlantInfo>) {
        this.plantInfoList.apply {
            clear()
            addAll(plantInfoList)
        }
    }

    inner class ViewHolder(binding: ItemPlantBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private val picView: ImageView = binding.imgPlantPic
        private val nameView: TextView = binding.tvPlantName
        private val infoView: TextView = binding.tvPlantAlsoKnown

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(plantInfo: PlantInfo) {
            nameView.text = plantInfo.nameCh
            infoView.text = plantInfo.alsoKnown
            picView.loadRoundRecImage(plantInfo.pic01Url, R.drawable.ic_cactus)
        }

        override fun onClick(v: View?) {
            onItemClickListener.onItemClick(plantInfoList[absoluteAdapterPosition])
        }
    }

}