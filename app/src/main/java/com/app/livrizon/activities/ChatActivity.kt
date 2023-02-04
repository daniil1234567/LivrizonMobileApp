package com.app.livrizon.activities

import androidx.activity.viewModels
import com.app.livrizon.R
import com.app.livrizon.view_model.ViewModel

class ChatActivity : CustomActivity() {
    private val viewModel: ViewModel by viewModels()

    override fun getStartDestination(): Int {
        return R.id.chatFragment
    }

    override fun getGraph(): Int {
        return R.navigation.chat_graph
    }

    override fun onBackPressed() {
        if (viewModel.action.value == true) viewModel.action.value = false
        else super.onBackPressed()
    }
}