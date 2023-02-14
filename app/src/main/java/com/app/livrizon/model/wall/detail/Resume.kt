package com.app.livrizon.model.wall.detail

import com.app.livrizon.model.type.work.ChartType
import com.app.livrizon.model.type.work.EmploymentType


class Resume(
    var positions: List<String>,
    var charts: List<ChartType>,
    var employments: List<EmploymentType>,
    var regions: List<String>,
) : java.io.Serializable