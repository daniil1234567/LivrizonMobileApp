package com.app.livrizon.model.authorization

import com.app.livrizon.impl.Base

data class Categories (
    var title: String,
    var descr: String
) : Base {


    companion object {
       var title = String
        var descr = String
    }


}