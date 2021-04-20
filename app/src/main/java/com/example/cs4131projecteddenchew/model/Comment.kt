package com.example.cs4131projecteddenchew.model

import com.google.firebase.database.IgnoreExtraProperties
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@IgnoreExtraProperties
class Comment {
    var id: Long? = null
    var comment: String? = null
    var commenter:String? = null
    var timeString: String? = null
    var time: Date? = null

    constructor(id: Long?, comment: String?, commenter:String?, time: Date?) {
        this.id = id
        this.comment = comment
        this.time = time
        this.commenter = commenter

        val df: DateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        this.timeString = df.format(time)
    }

    constructor(id_: Long?, comment_: String?, commenter:String?, timeString_: String) {
        this.id = id_
        this.comment = comment_
        this.timeString = timeString_
        this.commenter = commenter

        val df: DateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

        this.time = df.parse(timeString_)
    }

    constructor()


}