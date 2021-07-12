package com.example.zoo.page.arealist

import com.example.zoo.page.BasePresenter
import com.example.zoo.page.BaseView
import com.example.zoo.model.remote.data.AreaInfo

interface AreaListContract {
    interface View : BaseView<Presenter?> {
        fun refreshData(areaInfoList: List<AreaInfo>)
        fun showProgress()
        fun hideProgress()
        fun showErrorView()
        fun showEmptyView()
    }

    interface Presenter : BasePresenter {
        fun loadData()
    }
}