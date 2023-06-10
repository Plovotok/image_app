package com.example.imageapp.image

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.imageapp.image.recyclerview.ImageItem
import com.example.imageapp.image.recyclerview.ImageViewAdapter
import com.example.imageapp.image.retrofit.ApiInterface
import com.example.imageapp.image.retrofit.Data
import com.example.imageapp.image.retrofit.ImageDataClass
import com.example.pictures.forRV.PictureAdapter
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ImageViewModel(application: Application) : AndroidViewModel(application) {
    val BASE_URL = "https://api.yooyo.ru/"

    @SuppressLint("StaticFieldLeak")
    lateinit var rvPics : RecyclerView
    lateinit var rvLiveData : MutableLiveData<RecyclerView>
    lateinit var adapter : ImageViewAdapter
    var _itemList = MutableLiveData<List<Data>>()
//    lateinit var itemList: LiveData<List<Data>>



    @SuppressLint("StaticFieldLeak")
    val context = application.applicationContext

    fun getAllData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)


        val retrofitData = retrofit.getData()
        retrofitData.enqueue(object : retrofit2.Callback<ImageDataClass> {
            override fun onResponse(
                call: Call<ImageDataClass>,
                response: Response<ImageDataClass>
            ) {
                var body = response.body()
                val data = body?.data
                adapter = ImageViewAdapter(context)
                try {
                    adapter.itemList = data!!
//                    _itemList.value = data!!

//                    adapter.setData(data!!)
                    _itemList.value = data!!


                    Log.d("response", "${response.body()}")
                } catch (e : NullPointerException) {
                    Log.e("npe", "NullPointerException")
                }

            }

            override fun onFailure(call: Call<ImageDataClass>, t: Throwable) {

            }

        })
    }



}