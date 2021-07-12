package com.example.zoo.page.plantInfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.zoo.R
import com.example.zoo.databinding.ActivityBaseBinding
import com.example.zoo.utils.addToActivity

class PlantInfoActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( ActivityBaseBinding.inflate(layoutInflater).root)

        var plantInfoFragment: PlantInfoFragment? = supportFragmentManager
            .findFragmentById(R.id.contentFrame) as PlantInfoFragment?

        if (plantInfoFragment == null) {
            plantInfoFragment = PlantInfoFragment.newInstance()
            plantInfoFragment.addToActivity(supportFragmentManager, R.id.contentFrame)
        }

        plantInfoFragment.setPresenter(PlantInfoPresenter(plantInfoFragment, intent.extras))
    }
}