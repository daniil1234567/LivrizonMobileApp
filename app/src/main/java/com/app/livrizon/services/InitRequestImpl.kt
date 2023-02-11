package com.app.livrizon.services

import com.app.livrizon.model.chat.Chat
import com.app.livrizon.model.init.*
import com.app.livrizon.model.profile.Profile
import com.app.livrizon.model.profile.Subscribe
import com.app.livrizon.model.publication.*
import com.app.livrizon.request.Filter
import com.app.livrizon.request.Sub
import com.app.livrizon.security.Role

interface InitRequestImpl {
    suspend fun profiles(role: Role?, filter: Filter, my_sub: Boolean?, limit: Int): Array<Profile>
    suspend fun profileSearch(): InitProfileSearch
    suspend fun sub(sub: Sub, profile_id: Int, filter: Filter?): Array<Subscribe>
    suspend fun messages(profile_id: Int, message_id: Int?): Array<Message>
    suspend fun chat(profile_id: Int): InitChat
    suspend fun chats(): Array<Chat>
    suspend fun append(): Array<Profile>
    suspend fun messenger(): InitMessenger
    suspend fun articles(): Array<Article>
    suspend fun authors(): Array<Author>
    suspend fun popular(): Popular?
    suspend fun home(): Array<Post>
}