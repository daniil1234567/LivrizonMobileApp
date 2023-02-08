package com.app.livrizon.services

import com.app.livrizon.model.chat.Chat
import com.app.livrizon.model.edit.profile.save.TeamSave
import com.app.livrizon.model.response.Response

interface TeamRequestImpl {
    suspend fun saveTeam(save: TeamSave): Response
}