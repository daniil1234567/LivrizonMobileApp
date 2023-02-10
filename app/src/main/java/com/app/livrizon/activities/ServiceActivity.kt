package com.app.livrizon.activities

import com.app.livrizon.R

class ServiceActivity : CustomActivity() {
    override fun getStartDestination(): Int {
        return when (key) {
            else -> R.id.articlesFragment
        }
    }

    companion object {
        const val networking = 0
        const val articles = 1
        const val resumes = 2
        const val vacancies = 3
        const val polls = 4
        const val statistic = 5
    }
}