package com.sarrawi.myimagesni

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.sarrawi.myimagesni.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val retrofitService = ApiService.provideRetrofitInstance()
    private val mainRepository by lazy { ImgRepository(retrofitService, this.application) }
    private val imgsViewModel: Imgs_ViewModel by viewModels {
        ViewModelFactory(this, mainRepository)
    }
    private val imgAdapter by lazy { ImgAdapter(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRvSnipp()
    }



    fun setupRvSnipp() {
//        imgsViewModel.imgsList.observe(this, Observer { imgsList ->
//            // قم بتحديث RecyclerView Adapter بناءً على البيانات الجديدة
////            imgAdapter.submitList(imgsList)
//
//            imgAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.ALLOW
//            imgAdapter.img_list = imgsList
//
//            if (binding.rvImgCont.adapter == null) {
//                binding.rvImgCont.layoutManager = GridLayoutManager(this, 2)
//                binding.rvImgCont.adapter = imgAdapter
//            } else {
//                imgAdapter.notifyDataSetChanged()
//            }
//        })
//
//        imgsViewModel.getSnippets()


//        imgsViewModel.getSnippetss().observe(this) { imgs ->
//            // الكود الحالي هنا
//            imgAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.ALLOW
//            imgAdapter.img_list = imgs
//
//            if (binding.rvImgCont.adapter == null) {
//                binding.rvImgCont.layoutManager = GridLayoutManager(this, 2)
//                binding.rvImgCont.adapter = imgAdapter
//            } else {
//                imgAdapter.notifyDataSetChanged()
//            }
//        }

//        imgsViewModel.imageModelResponse.observe(this, { imageModelResponse ->
//            // قم بتحديث RecyclerView هنا باستخدام البيانات الجديدة
//            imgAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.ALLOW
//            imgAdapter.updateData(imageModelResponse.results)
//            if (imageModelResponse != null) {
//                binding.rvImgCont.layoutManager = GridLayoutManager(this, 2)
//                binding.rvImgCont.adapter = imgAdapter
//            }
//
//             else {
//                imgAdapter.notifyDataSetChanged()
//            }
////            if (imageModelResponse != null) {
////                val adapter = MyAdapter(imageModelResponse.imageModelList)
////                recyclerView.adapter = adapter
////            }
//        })

//        imgsViewModel.imageModelResponse.observe(this) { imageModelResponse ->
//            // قم بتحديث RecyclerView هنا باستخدام البيانات الجديدة
//            imgAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.ALLOW
//
//            if (imageModelResponse != null) {
//                imgAdapter.updateData(imageModelResponse.results)
//                imgAdapter.differ.submitList(imageModelResponse.results)
//                binding.rvImgCont.layoutManager = GridLayoutManager(this, 2)
//                binding.rvImgCont.adapter = imgAdapter
//            } else {
//                imgAdapter.notifyDataSetChanged()
//            }
//        }
//
//
//        imgsViewModel.fetchData()
//
//
//    }

// تعيين المحدث خارج كتلة التحقق
        imgAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.ALLOW
        binding.rvImgCont.layoutManager = GridLayoutManager(this, 2)
        binding.rvImgCont.adapter = imgAdapter

        imgsViewModel.imageModelResponse.observe(this) { imageModelResponse ->
            // قم بتحديث RecyclerView هنا باستخدام البيانات الجديدة
            if (imageModelResponse != null) {
                imgAdapter.updateData(imageModelResponse.results)
                imgAdapter.differ.submitList(imageModelResponse.results)
            } else {
                imgAdapter.notifyDataSetChanged()
            }
        }

        imgsViewModel.fetchData()
    }

}

