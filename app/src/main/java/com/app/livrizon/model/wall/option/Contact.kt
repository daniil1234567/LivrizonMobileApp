package com.app.livrizon.model.wall.option

import com.app.livrizon.model.type.ContactType
import com.app.livrizon.model.wall.InformationImpl

class Contact(
    val contact_id: Int,
    val type: ContactType,
    val contact: String
) : InformationImpl {
    override fun layout(): Int {
        return InformationImpl.contact
    }
    override fun id(): Int {
        return contact_id
    }
}