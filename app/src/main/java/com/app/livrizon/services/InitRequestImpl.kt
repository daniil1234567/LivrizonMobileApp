package com.app.livrizon.services

import com.app.livrizon.model.chat.Chat
import com.app.livrizon.model.init.*
import com.app.livrizon.model.profile.Profile
import com.app.livrizon.model.profile.Subscribe
import com.app.livrizon.model.publication.Message
import com.app.livrizon.model.publication.Post
import com.app.livrizon.values.Filter
import com.app.livrizon.values.Selection

interface InitRequestImpl {
    suspend fun services(): ServiceInit
    suspend fun profileSearch(): InitProfileSearch
    suspend fun profiles(selection: Selection, my_syb: Boolean?): Array<Profile>
    suspend fun sub(selection: Selection, profile_id: Int, filter: Filter?): Array<Subscribe>
    suspend fun messages(profile_id: Int, message_id: Int?): Array<Message>
    suspend fun chat(profile_id: Int): InitChat
    suspend fun chats(): Array<Chat>
    suspend fun append(): Array<Profile>
    suspend fun messenger(): InitMessenger
    suspend fun news(): InitNews
    suspend fun home(): Array<Post>
}