package com.app.livrizon.model.place

import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(
      val latitude:Float,
      val longitude:Float
)