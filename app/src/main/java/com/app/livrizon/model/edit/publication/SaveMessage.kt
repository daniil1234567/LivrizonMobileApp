package com.app.livrizon.model.edit.publication

import com.app.livrizon.model.chat.option.Forward
import com.app.livrizon.model.publication.option.Address
import com.app.livrizon.model.publication.option.Poll

import com.app.livrizon.model.type.PublicationType


class SaveMessage(
    val link: Boolean,
    val address: Address?,
    val poll: Poll?,
    val forward: Forward?,
    description: String
) : SavePublicationBase(description)