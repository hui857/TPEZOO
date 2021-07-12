package com.example.zoo.page.areainfo

import com.example.zoo.page.BasePresenter
import com.example.zoo.page.BaseView
import com.example.zoo.model.remote.data.PlantInfo
import com.example.zoo.page.BaseToolbarImageView

interface AreaInfoContract {
    interface View : BaseToolbarImageView<Presenter?> {
        fun showPlantInfoList(plantInfoList: List<PlantInfo>)
        fun showAreaInfo(info: String)
        fun showMemo(memo: String)
        fun showCategory(category: String)
        fun startWeb(url: String)
        fun showProgress()
        fun hideProgress()
        fun showErrorView()
        fun showEmptyView()
    }

    interface Presenter : BasePresenter {
        fun clickWeb()
        fun loadPlantData()
    }
}