package com.app.livrizon.model.place

import kotlinx.serialization.Serializable

@Serializable
data class Place (
    val point: Coordinates,
    val address: String
) : java.io.Serializable
