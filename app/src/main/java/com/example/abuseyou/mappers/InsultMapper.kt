package com.example.abuseyou.mappers

import com.example.abuseyou.api.model.InsultModel
import com.example.abuseyou.db.model.AbuseModel

object InsultMapper {

    fun apiToDbInsultModel(apiModel: InsultModel) = AbuseModel(
        id = 0,
        number = apiModel.number,
        language = apiModel.language,
        insult = apiModel.insult,
        created = apiModel.created,
        shown = apiModel.shown,
        createdby = apiModel.createdby,
        active = apiModel.active,
        comment = apiModel.comment,
    )

    fun dbToApiInsultModel(dbModel: AbuseModel) = InsultModel(
        number = dbModel.number,
        language = dbModel.language,
        insult = dbModel.insult,
        created = dbModel.created,
        shown = dbModel.shown,
        createdby = dbModel.createdby,
        active = dbModel.active,
        comment = dbModel.comment,
    )
}
