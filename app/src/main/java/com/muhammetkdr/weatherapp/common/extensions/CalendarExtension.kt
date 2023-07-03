package com.muhammetkdr.weatherapp.common.extensions

import java.util.Calendar

//Destructuring declarations
operator fun Calendar.component1() = get(Calendar.DATE)
operator fun Calendar.component2() = get(Calendar.MONTH)+1
operator fun Calendar.component3() = get(Calendar.YEAR)

