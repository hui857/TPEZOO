package com.example.zoo.page.plantInfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zoo.R
import com.example.zoo.databinding.FragmentPlantInfoBinding
import com.example.zoo.databinding.ToolBarImageBinding
import com.example.zoo.model.remote.data.PlantInfo
import com.example.zoo.page.AbstractToolbarImageFragment

class PlantInfoFragment : AbstractToolbarImageFragment(), PlantInfoContract.View {

    private var presenter: PlantInfoContract.Presenter? = null

    private var _binding: FragmentPlantInfoBinding? = null

    private val binding get() = _binding!!

    companion object {
        @JvmStatic
        fun newInstance() = PlantInfoFragment()
    }

    override fun setPresenter(presenter: PlantInfoContract.Presenter?) {
        this.presenter = checkNotNull(presenter)
    }

    override fun createView(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentPlantInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun appbarImageBinding(): ToolBarImageBinding {
        return binding.appBarImageLayout
    }

    override fun headerImagePlaceholder(): Int {
       return R.drawable.ic_cactus
    }


    override fun onResume() {
        super.onResume()
        presenter.apply {
            this?.onResume()
        }
    }

    override fun showPlantInfo(plantInfo: PlantInfo) {
        binding.tvPlantEnName.text = plantInfo.nameEn
        binding.tvPlantAlsoKnown.text = plantInfo.alsoKnown
        binding.tvPlantBrief.text = plantInfo.brief
        binding.tvPlantFeature.text = plantInfo.feature
        binding.tvPlantApplication.text = plantInfo.application
        binding.tvPlantUpdateTime.text = plantInfo.update
    }
}