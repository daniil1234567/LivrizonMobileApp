package com.app.livrizon.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.app.livrizon.function.log

class DbHelper(context: Context):SQLiteOpenHelper(context, DbItem.Database.livrizon,null, DbItem.Database.version) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DbItem.Statement.Create.accounts)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL(DbItem.Statement.Drop.accounts)
        onCreate(db)
    }
}