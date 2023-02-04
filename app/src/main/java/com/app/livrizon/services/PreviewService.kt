package com.app.livrizon.services

import com.app.livrizon.model.chat.Chat

interface PreviewService {
    suspend fun chat(profile_id: Int):Chat
}