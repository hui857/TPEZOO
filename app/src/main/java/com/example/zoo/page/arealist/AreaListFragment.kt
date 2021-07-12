package com.example.zoo.page.arealist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zoo.R
import com.example.zoo.databinding.FragmentAreaListBinding
import com.example.zoo.model.remote.data.AreaInfo
import com.example.zoo.page.OnItemClickListener
import com.example.zoo.page.areainfo.AreaInfoActivity


class AreaListFragment : Fragment(), AreaListContract.View, OnItemClickListener<AreaInfo> {

    private var presenter: AreaListContract.Presenter? = null

    private var areaListAdapter: AreaListAdapter?=null

    private var _binding: FragmentAreaListBinding? = null

    private val binding get() = _binding!!

    companion object {
        @JvmStatic
        fun newInstance() = AreaListFragment()
    }

    override fun setPresenter(presenter: AreaListContract.Presenter?) {
        this.presenter = checkNotNull(presenter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAreaListBinding.inflate(inflater, container, false)
        with(binding.list){
            layoutManager = LinearLayoutManager(context)
            areaListAdapter = AreaListAdapter(arrayListOf(), this@AreaListFragment)
            adapter = areaListAdapter
        }
        binding.toolbar.setTitle(R.string.app_name)

        with(binding.groupErrorInfo.btnTry){
            setOnClickListener {
                presenter.apply {
                    this?.loadData()
                }
            }
        }
        return binding.root
    }


    override fun refreshData(areaInfoList: List<AreaInfo>) {
        binding.list.visibility = View.VISIBLE
        areaListAdapter?.apply {
            setData(areaInfoList)
            notifyDataSetChanged()
        }
    }

    override fun showProgress() {
        binding.progressCircular.visibility = View.VISIBLE
        binding.list.visibility = View.GONE
        binding.groupErrorInfo.root.visibility = View.GONE
        binding.groupEmptyInfo.root.visibility = View.GONE
    }

    override fun hideProgress() {
        binding.progressCircular.visibility = View.GONE
    }

    override fun showErrorView() {
        binding.groupErrorInfo.root.visibility = View.VISIBLE
        binding.list.visibility = View.GONE
    }

    override fun showEmptyView() {
        binding.groupEmptyInfo.root.visibility = View.VISIBLE
        binding.list.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        presenter.apply {
            this?.onResume()
        }
    }

    override fun onItemClick(data: AreaInfo) {
        activity?.let{
            val intent = Intent (it, AreaInfoActivity::class.java)
            val bundle = bundleOf("areaInfo" to data)
            intent.putExtras(bundle)
            it.startActivity(intent)
        }
    }
}