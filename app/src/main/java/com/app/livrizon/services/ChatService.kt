package com.app.livrizon.services

import com.app.livrizon.model.response.Response
import com.app.livrizon.model.edit.publication.SaveMessage

interface ChatService {
    suspend fun deleteMessages(profile_id: Int, ids: MutableList<Int>):Response
    suspend fun sendMessage(
        profile_id: Int, message: SaveMessage
    ): Response
}