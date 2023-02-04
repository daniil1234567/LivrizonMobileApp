package com.app.livrizon.services

import com.app.livrizon.model.chat.Chat
import com.app.livrizon.model.init.InitChat
import com.app.livrizon.model.init.InitMessenger
import com.app.livrizon.model.init.InitNews
import com.app.livrizon.model.init.InitProfileSearch
import com.app.livrizon.model.profile.Profile
import com.app.livrizon.model.profile.Recommendation
import com.app.livrizon.model.profile.Subscribe
import com.app.livrizon.model.publication.Message
import com.app.livrizon.model.publication.Post
import com.app.livrizon.values.Filter
import com.app.livrizon.values.Selection

interface InitService {
    suspend fun profileSearch(): InitProfileSearch
    suspend fun profileRecommendation():Array<Recommendation>
    suspend fun sub(selection: Selection, profile_id: Int,filter: Filter?): Array<Subscribe>
    suspend fun messages(profile_id: Int, message_id: Int?): Array<Message>
    suspend fun chats(): Array<Chat>
    suspend fun append(): Array<Profile>
    suspend fun messenger(): InitMessenger
    suspend fun chat(profile_id: Int): InitChat
    suspend fun news(): InitNews
    suspend fun home(): Array<Post>
}