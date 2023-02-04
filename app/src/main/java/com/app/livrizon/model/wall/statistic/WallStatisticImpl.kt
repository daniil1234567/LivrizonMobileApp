package com.app.livrizon.model.wall.statistic

interface WallStatisticImpl : java.io.Serializable {
    fun followers():Int?
    fun publications():Int
}