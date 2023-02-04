package com.app.livrizon.model.chat.option

import com.app.livrizon.impl.Base
import com.app.livrizon.impl.CreateImpl

abstract class AttachmentBase(val id: Int, var create: Boolean = false) : CreateImpl,Base {
    override fun create(): Boolean {
        return create
    }
    override fun id(): Int {
        return id
    }

    companion object {
        const val repost = 1
        const val poll = 1
    }
}