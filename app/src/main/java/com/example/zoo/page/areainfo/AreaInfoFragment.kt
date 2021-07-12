package com.example.zoo.page.areainfo

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zoo.R
import com.example.zoo.databinding.FragmentAreaInfoBinding
import com.example.zoo.databinding.ToolBarImageBinding
import com.example.zoo.model.remote.data.PlantInfo
import com.example.zoo.model.repository.ZooRepository
import com.example.zoo.page.AbstractToolbarImageFragment
import com.example.zoo.page.OnItemClickListener
import com.example.zoo.page.plantInfo.PlantInfoActivity
import com.example.zoo.utils.setMemo

class AreaInfoFragment : AbstractToolbarImageFragment(), AreaInfoContract.View {

    private var presenter: AreaInfoContract.Presenter? = null

    private var plantListAdapter: PlantListAdapter?=null

    private var _binding: FragmentAreaInfoBinding? = null

    private val binding get() = _binding!!

    companion object {
        @JvmStatic
        fun newInstance() = AreaInfoFragment()
    }

    override fun setPresenter(presenter: AreaInfoContract.Presenter?) {
        this.presenter = checkNotNull(presenter)
    }

    override fun createView(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentAreaInfoBinding.inflate(inflater, container, false)
        with(binding.list){
            layoutManager = LinearLayoutManager(context)
            plantListAdapter = PlantListAdapter(arrayListOf(), object : OnItemClickListener<PlantInfo> {
                override fun onItemClick(data: PlantInfo) {
                    val intent = Intent (activity, PlantInfoActivity::class.java)
                    val bundle = bundleOf("plantInfo" to data)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
            })
            adapter = plantListAdapter
        }

        with(binding.tvAreaWeb){
            setOnClickListener {
                presenter?.apply {
                    this.clickWeb()
                }
            }
        }

        with(binding.groupErrorInfo.btnTry){
            setOnClickListener {
                presenter?.apply {
                    this.loadPlantData()
                }
            }
        }

        return binding.root
    }

    override fun headerImagePlaceholder(): Int {
        return R.drawable.ic_koala
    }

    override fun hideProgress() {
        binding.progressCircular.visibility = View.GONE
    }

    override fun showProgress() {
        binding.progressCircular.visibility = View.VISIBLE
        binding.list.visibility = View.GONE
        binding.groupErrorInfo.root.visibility = View.GONE
        binding.groupEmptyInfo.root.visibility = View.GONE
    }

    override fun showErrorView() {
        binding.groupErrorInfo.root.visibility = View.VISIBLE
        binding.list.visibility = View.GONE
    }

    override fun showEmptyView() {
        binding.groupEmptyInfo.root.visibility = View.VISIBLE
        binding.list.visibility = View.GONE
    }

    override fun startWeb(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun showPlantInfoList(plantInfoList: List<PlantInfo>) {
        binding.list.visibility = View.VISIBLE
        plantListAdapter?.apply {
            setData(plantInfoList)
            notifyItemRangeChanged(0, plantInfoList.size)
        }
    }

    override fun showAreaInfo(info: String) {
        binding.tvAreaInfo.text = info
    }

    override fun showMemo(memo: String) {
        binding.tvAreaMemo.setMemo(memo.takeIf { it.isNotEmpty() })
    }

    override fun showCategory(category: String) {
        binding.tvAreaCategory.text = category
    }

    override fun onResume() {
        super.onResume()
        presenter.apply {
            this?.onResume()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun appbarImageBinding(): ToolBarImageBinding {
        return binding.appBarImageLayout
    }

}