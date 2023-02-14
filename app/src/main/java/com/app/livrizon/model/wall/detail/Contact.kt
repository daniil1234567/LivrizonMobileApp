package com.app.livrizon.model.wall.detail

import com.app.livrizon.impl.Base
import com.app.livrizon.model.type.ContactType
import com.app.livrizon.model.wall.WallInformation

class Contact(
    val contact_id: Int,
    val type: ContactType,
    val contact: String
) : Base {
    override fun layout(): Int {
        return WallInformation.contact
    }
    override fun id(): Int {
        return contact_id
    }
}