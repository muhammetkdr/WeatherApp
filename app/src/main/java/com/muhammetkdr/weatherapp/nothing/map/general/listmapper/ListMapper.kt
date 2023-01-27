package com.muhammetkdr.weatherapp.nothing.map.general.listmapper

import com.muhammetkdr.weatherapp.nothing.map.general.done.Mapper

interface ListMapper<in I, out O> : Mapper<List<I>, List<O>>