package com.example.zoo.page.areainfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.zoo.R
import com.example.zoo.databinding.ActivityBaseBinding
import com.example.zoo.model.remote.ZooAPIClient
import com.example.zoo.model.remote.apiservice.ZooApiService
import com.example.zoo.model.repository.ZooRepository
import com.example.zoo.utils.addToActivity

class AreaInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( ActivityBaseBinding.inflate(layoutInflater).root)

        var areaInfoFragmentFragment: AreaInfoFragment? = supportFragmentManager
            .findFragmentById(R.id.contentFrame) as AreaInfoFragment?

        if (areaInfoFragmentFragment == null) {
            areaInfoFragmentFragment = AreaInfoFragment.newInstance()
            areaInfoFragmentFragment.addToActivity(supportFragmentManager, R.id.contentFrame)
        }

        areaInfoFragmentFragment.setPresenter(AreaInfoPresenter(areaInfoFragmentFragment,
            intent.extras, ZooRepository.newInstance()))
    }
}