package com.app.livrizon.services

import com.app.livrizon.model.chat.Chat
import com.app.livrizon.model.edit.profile.save.TeamSave

interface TeamService {
    suspend fun saveTeam(save: TeamSave): Chat
}