package com.app.livrizon.impl

import com.app.livrizon.model.file.Photo

interface LinkImpl {
     fun domain(): String?
     fun url(): String?
     fun title(): String?
     fun description(): String?
     fun photo(): Photo?
}