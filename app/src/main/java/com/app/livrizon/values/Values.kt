package com.app.livrizon.request

import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import com.app.livrizon.function.createHttpClient
import com.app.livrizon.security.token.TokenBase
import com.google.gson.Gson
import com.google.gson.GsonBuilder

val httpClient = createHttpClient()
val gson: Gson = GsonBuilder().create()
lateinit var connection: SQLiteDatabase
lateinit var account_pref: SharedPreferences
lateinit var token: TokenBase
var second: Long = 1000
var minute: Long = 60 * second
var hour: Long = 60 * minute
var day: Long = 24 * hour
var week: Long = 7 * day
var year: Long = 365 * day
