package com.muhammetkdr.weatherapp.nothing.map.general.listmapper

import com.muhammetkdr.weatherapp.nothing.map.general.done.Mapper

class ListMapperImpl<in I, out O>(
    private val mapper: Mapper<I, O>
) : ListMapper<I, O> {
    override fun map(input: List<I>): List<O> =
        input.map(mapper::map)
}