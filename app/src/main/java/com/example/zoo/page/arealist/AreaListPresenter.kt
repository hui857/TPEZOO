package com.example.zoo.page.arealist

import android.util.Log
import com.example.zoo.ZooApplication
import com.example.zoo.model.remote.data.AreaInfo
import com.example.zoo.model.repository.ZooRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AreaListPresenter(private val view: AreaListContract.View, private val zooRepository: ZooRepository):
    AreaListContract.Presenter {

    override fun onResume() {
        loadData()
    }

    override fun loadData() {
        zooRepository.getAreas(ZooApplication.instance())
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<AreaInfo>> {
                override fun onSubscribe(d: Disposable) {
                    view.showProgress()
                }

                override fun onNext(areaInfoList: List<AreaInfo>) {
                    view.hideProgress()

                    if(areaInfoList.isEmpty()){
                        view.showEmptyView()
                    }else{
                        view.refreshData(areaInfoList)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d("CandyLog", "error: $e")
                    view.hideProgress()
                    view.showErrorView()
                }

                override fun onComplete() {
                }
            })
    }

    override fun onDestroy() {
    }
}