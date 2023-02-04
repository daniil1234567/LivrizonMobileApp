package com.app.livrizon.model.wall

class ResumeBase(
    val positions: String,
    val regions: String,
    val employments: String,
    val charts: String
):InformationImpl{
    override fun id(): Int {
        return 1
    }

}