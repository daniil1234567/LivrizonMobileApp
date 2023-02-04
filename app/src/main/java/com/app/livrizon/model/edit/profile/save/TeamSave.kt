package com.app.livrizon.model.edit.profile.save

import com.app.livrizon.model.file.Crop
import com.app.livrizon.security.Level

class TeamSave : java.io.Serializable {
    lateinit var name: String
    var title: String? = null
    var crop: Crop? = null
    var description: String? = null
    val members= mutableListOf<Int>()
    var edit: Int = Level.it_sub
    var append: Int = Level.it_sub
    var invite: Int = Level.usual
    var write: Int = Level.it_sub
    var statistic_visibility: Int = Level.usual
    var sub_visibility: Int = Level.usual
    var access: Int = Level.usual
}