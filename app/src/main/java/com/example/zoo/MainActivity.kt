package com.example.zoo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.zoo.databinding.ActivityBaseBinding
import com.example.zoo.model.repository.ZooRepository
import com.example.zoo.page.arealist.AreaListFragment
import com.example.zoo.page.arealist.AreaListPresenter
import com.example.zoo.utils.addToActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityBaseBinding.inflate(layoutInflater).root)

        var areaListFragment: AreaListFragment? = supportFragmentManager
            .findFragmentById(R.id.contentFrame) as AreaListFragment?

        if (areaListFragment == null) {
            areaListFragment = AreaListFragment.newInstance()
            areaListFragment.addToActivity(supportFragmentManager, R.id.contentFrame)
        }
        areaListFragment.setPresenter(AreaListPresenter(areaListFragment,  ZooRepository.newInstance()))
    }
}