package com.app.livrizon.model.edit.profile.save

import com.app.livrizon.model.file.Crop
import com.app.livrizon.security.Role

class AccountSave : java.io.Serializable {
    lateinit var role: Role
    lateinit var password: String
    var crop: Crop? = null
    lateinit var name: String
    var birthday: String? = null
}