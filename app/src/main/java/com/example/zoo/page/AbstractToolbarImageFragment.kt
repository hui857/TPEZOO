package com.example.zoo.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.zoo.databinding.ToolBarImageBinding
import com.example.zoo.utils.loadHeaderImage

abstract class AbstractToolbarImageFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val createView = createView(inflater, container)
        with(appbarImageBinding().toolbar){
            setNavigationOnClickListener { activity?.onBackPressed() }
        }
        return createView
    }

     fun showTitle(chName: String) {
         appbarImageBinding().toolbar.title = chName
    }

     fun showHeaderPic(picUrl: String) {
         appbarImageBinding().imgPic.loadHeaderImage(picUrl,headerImagePlaceholder())
    }

    abstract fun createView(inflater: LayoutInflater, container: ViewGroup?):View
    abstract fun appbarImageBinding(): ToolBarImageBinding
    abstract fun headerImagePlaceholder(): Int
}