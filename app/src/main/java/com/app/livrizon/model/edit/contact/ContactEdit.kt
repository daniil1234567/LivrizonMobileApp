package com.app.livrizon.model.edit.contact

import com.app.livrizon.model.type.ContactType


class ContactEdit(
    var contact_id: Int?,
    var type: ContactType?,
    var contact: String?,
) : java.io.Serializable