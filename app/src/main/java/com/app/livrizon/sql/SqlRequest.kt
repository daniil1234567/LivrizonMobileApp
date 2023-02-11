package com.app.livrizon.sql

import android.content.ContentValues
import com.app.livrizon.function.query
import com.app.livrizon.model.authorization.Authentication
import com.app.livrizon.request.connection
import com.app.livrizon.services.SqlRequestImpl

object SqlRequest : SqlRequestImpl {
    override fun saveAccount(account: Authentication) {
        val values = ContentValues().apply {
            put(DbItem.Column.username, account.username)
            put(DbItem.Column.password, account.password)
        }
        connection.replace(DbItem.Table.accounts, null, values)
    }

    override fun getMyAccounts(): MutableList<Authentication>? {
        val accounts = mutableListOf<Authentication>()
        val cursor = query(DbItem.Table.accounts, arrayOf(DbItem.Column.username, DbItem.Column.password))

        while (cursor.moveToNext()) {
            accounts.add(Authentication(cursor))
        }
        cursor.close()
        return if(accounts.size>0) accounts
        else null
    }

    override fun getAccount(username:String): Authentication {
        val cursor = query(DbItem.Table.accounts, arrayOf(DbItem.Column.username, DbItem.Column.password),
            arrayOf(DbItem.Column.username), arrayOf(username))
        cursor.moveToNext()
        return Authentication(cursor)
    }
}