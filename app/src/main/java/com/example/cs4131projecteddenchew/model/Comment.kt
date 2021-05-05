package com.example.cs4131projecteddenchew.model

import com.google.firebase.database.IgnoreExtraProperties
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@IgnoreExtraProperties
class Comment(var id: Long? = null, var comment: String? = null, var commenter: String? = null, var time: Date? = null) {
    var timeString: String?

    companion object {
        val df: DateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
    }

    init {
        this.timeString = df.format(time)
    }

    constructor(id: Long?, comment: String?, commenter: String?, timeString: String?): this(id, comment, commenter, df.parse(timeString)) {
        this.timeString = timeString
    }

    override fun toString() = "Comment(id=$id, comment=$comment, commenter=$commenter, timeString=$timeString, time=$time)"
}
