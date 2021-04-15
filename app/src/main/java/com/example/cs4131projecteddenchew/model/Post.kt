package com.example.cs4131projecteddenchew.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
public class Post {
    var id: Long? = null
    var posterId: String? = null
    var questionStatement: String? = null
    var source: String? = null
    var qnType: Long? = null
    var targettedLevel: Long? = null
    var answer: String? = null
    var explaination: String? = null
    var comments: List<Long>? = null
    var tags: List<String>? = null
    var published: Boolean? = null



    constructor(id: Long?, posterId: String?, questionStatement: String?, source: String?, qnType: Long?,
                answer: String?, explaination: String?, comments: List<Long>?, tags: List<String>?, published:Boolean?) {
        this.id = id
        this.posterId = posterId
        this.questionStatement = questionStatement
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

    override fun toString(): String {
        return "Post(id=$id, posterId=$posterId, questionStatement=$questionStatement, source=$source, qnType=$qnType, targettedLevel=$targettedLevel, answer=$answer, explaination=$explaination, comments=$comments, tags=$tags, published=$published)"
    }
}