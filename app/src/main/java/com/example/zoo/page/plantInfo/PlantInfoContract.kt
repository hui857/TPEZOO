package com.example.zoo.page.plantInfo

import com.example.zoo.model.remote.data.PlantInfo
import com.example.zoo.page.BasePresenter
import com.example.zoo.page.BaseToolbarImageView
import com.example.zoo.page.BaseView

interface PlantInfoContract {
    interface View : BaseToolbarImageView<Presenter?> {
        fun showPlantInfo(plantInfo: PlantInfo)
    }

    interface Presenter : BasePresenter {
    }
}