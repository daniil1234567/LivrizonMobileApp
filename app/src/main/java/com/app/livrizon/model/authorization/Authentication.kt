package com.app.livrizon.model.authorization

import android.annotation.SuppressLint
import android.database.Cursor
import com.app.livrizon.sql.DbItem
import kotlinx.serialization.Serializable

@Serializable
data class Authentication(
    val username: String,
    var password: String
) {
    @SuppressLint("Range")
    constructor(cursor: Cursor) : this(
        cursor.getString(cursor.getColumnIndex(DbItem.Column.username)),
        cursor.getString(cursor.getColumnIndex(DbItem.Column.password))
    )
}