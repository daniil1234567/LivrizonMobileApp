package com.app.livrizon.impl

interface Base : java.io.Serializable {
    fun id(): Int?
    fun equals(): Int {
        return id()!!
    }
    fun layout(): Int{
        return 0
    }
}