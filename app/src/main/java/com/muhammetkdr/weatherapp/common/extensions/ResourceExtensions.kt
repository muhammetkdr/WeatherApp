package com.muhammetkdr.weatherapp.common.extensions

import com.muhammetkdr.weatherapp.common.utils.Resource

fun <I:Any,O:Any> Resource<I>.mapResource(mapper:I.()-> O): Resource<O>{
    return when(this){
        is Resource.Error -> Resource.Error(this.error)
        is Resource.Success -> Resource.Success(mapper.invoke(this.data))
        is Resource.Loading -> Resource.Loading
    }
}