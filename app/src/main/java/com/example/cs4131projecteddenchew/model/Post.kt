package com.example.cs4131projecteddenchew.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
public class Post {
    var id: Long? = null
    var questionSrc: String? = null
    var source: String? = null
    var qnType: Long? = null
    var targettedLevel: Long? = null
    var answer: String? = null
    var explaination: String? = null
    var comments: List<Long>? = null
    var tags: List<Long>? = null
    var published: Boolean? = null



    constructor(id: Long?, questionSrc: String?, source: String?, qnType: Long?, targettedLevel: Long?, answer: String?, explaination: String?, comments: List<Long>?, tags: List<Long>?, published:Boolean?) {
        this.id = id
        this.questionSrc = questionSrc
        this.source = source
        this.qnType = qnType
        this.targettedLevel = targettedLevel
        this.answer = answer
        this.explaination = explaination
        this.comments = comments
        this.tags = tags
        this.published = published
    }

    constructor()
}