package com.example.zoo.page.arealist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zoo.R
import com.example.zoo.databinding.ItemAreaBinding
import com.example.zoo.model.remote.data.AreaInfo
import com.example.zoo.page.OnItemClickListener
import com.example.zoo.utils.loadRoundRecImage
import com.example.zoo.utils.setMemo

class AreaListAdapter(
    private val areaInfoList: MutableList<AreaInfo>, private  val onItemClickListener: OnItemClickListener<AreaInfo>
) : RecyclerView.Adapter<AreaListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemAreaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(areaInfoList[position])
    }

    override fun getItemCount(): Int = areaInfoList.size

    fun setData(areaInfoList: List<AreaInfo>) {
        this.areaInfoList.apply {
            clear()
            addAll(areaInfoList)
        }
    }

    inner class ViewHolder(binding: ItemAreaBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private val picView: ImageView = binding.imgAreaPic
        private val nameView: TextView = binding.tvAreaName
        private val infoView: TextView = binding.tvAreaInfo
        private val momoView: TextView = binding.tvAreaMemo

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(areaInfo: AreaInfo) {
            nameView.text = areaInfo.name
            infoView.text = areaInfo.info
            momoView.setMemo(areaInfo.memo.takeIf { it.isNotEmpty() })
            picView.loadRoundRecImage(areaInfo.picUrl, R.drawable.ic_koala)
        }

        override fun onClick(v: View?) {
            onItemClickListener.onItemClick(areaInfoList[absoluteAdapterPosition])
        }
    }

}