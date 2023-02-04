package com.app.livrizon.sql

object DbItem {
    object Database {
        const val version = 1
        const val livrizon = "livrizon.db"
    }

    object Column {
        const val username = "username"
        const val password = "password"
    }

    object Table {
        const val accounts = "accounts"
    }

    object Statement {
        object Create {
            const val accounts = "create table if not exists accounts(\n" +
                    "username varchar(50) not null unique,\n" +
                    "password text not null\n" +
                    ")"
        }

        object Drop {
            const val accounts = "drop table if exists account"
        }

        object Select{
            const val accounts="select username,password from accounts"
        }
        object Where{
            fun findBy(params: Array<String>?):String?{
                return if(params==null || params.isEmpty()) null
                else{
                    var a=""
                    for (i in params.indices) {
                        if(i!=0) a+="and "
                        a+="${params[i]}=? "
                    }
                    a
                }

            }
        }
    }

}