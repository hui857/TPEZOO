package com.example.zoo.page.areainfo

import android.os.Bundle
import android.util.Log
import com.example.zoo.ZooApplication
import com.example.zoo.model.remote.data.AreaInfo
import com.example.zoo.model.remote.data.PlantInfo
import com.example.zoo.model.repository.ZooRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AreaInfoPresenter(private val view: AreaInfoContract.View, private val bundle: Bundle?,
                        private val zooRepository: ZooRepository):
    AreaInfoContract.Presenter {


    private val areaInfo: AreaInfo by lazy {
        bundle?.get("areaInfo") as AreaInfo
    }

    override fun onResume() {
        loadHeaderPic()
        loadAreaInfo()
        loadPlantData()
    }

    private fun loadAreaInfo() {
        view.showTitle(areaInfo.name)
        view.showAreaInfo(areaInfo.info)
        view.showCategory(areaInfo.category)
        view.showMemo(areaInfo.memo)
    }

    private fun loadHeaderPic() {
        view.showHeaderPic(areaInfo.picUrl)
    }

    override fun loadPlantData() {
        zooRepository.getPlants(ZooApplication.instance(), areaInfo.name)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<PlantInfo>> {
                override fun onSubscribe(d: Disposable) {
                    view.showProgress()
                }

                override fun onNext(plantInfoList: List<PlantInfo>) {
                    view.hideProgress()
                    if(plantInfoList.isEmpty()){
                        view.showEmptyView()
                    }else{
                        view.showPlantInfoList(plantInfoList)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.e("CandyLog", ": $e")
                    view.hideProgress()
                    view.showErrorView()
                }

                override fun onComplete() {
                }
            })
    }


    override fun clickWeb() {
       view.startWeb(areaInfo.url)
    }

    override fun onDestroy() {
    }
}