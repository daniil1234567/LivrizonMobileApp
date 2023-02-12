package com.app.livrizon.model.authorization

import com.app.livrizon.impl.Base
import com.app.livrizon.impl.ChooseImpl
import com.app.livrizon.security.Role

class RegistrationRole(
    var choose: Boolean,
    val id: Int,
    val role: Role,
    val title: String,
    val description: String
) : Base, ChooseImpl {
    constructor(
        id: Int,
        role: Role,
        title: String,
        description: String
    ) : this(false, id, role, title, description)

    override fun id(): Int {
        return id
    }

    override fun choose(): Boolean {
        return choose
    }

}