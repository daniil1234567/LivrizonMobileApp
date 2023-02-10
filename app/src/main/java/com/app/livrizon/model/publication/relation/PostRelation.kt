package com.app.livrizon.model.publication.relation

import com.app.livrizon.model.publication.statistic.PublicationStatisticBase

class PostRelation(
    val bookmark: Boolean,
    seen: Boolean,
    my_reaction: Boolean
) : PublicationRelationBase(seen, my_reaction)