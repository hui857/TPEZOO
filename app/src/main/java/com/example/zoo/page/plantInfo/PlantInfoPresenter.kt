package com.example.zoo.page.plantInfo

import android.os.Bundle
import com.example.zoo.model.remote.data.PlantInfo


class PlantInfoPresenter(private val view: PlantInfoContract.View, private val bundle: Bundle?):
    PlantInfoContract.Presenter {

    private val plantInfo: PlantInfo by lazy {
        bundle?.get("plantInfo") as PlantInfo
    }

    override fun onResume() {
        loadHeaderPic()
        loadPlantInfo()
    }

    private fun loadPlantInfo() {
        view.showTitle(plantInfo.nameCh)
    }

    private fun loadHeaderPic() {
        view.showHeaderPic(plantInfo.pic01Url)
        view.showPlantInfo(plantInfo)
    }

    override fun onDestroy() {
    }
}