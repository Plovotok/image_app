package com.example.imageapp.image.retrofit

class Converter {

    fun getData(data : ImageDataClass): List<Data> {
        return data.data
    }
}