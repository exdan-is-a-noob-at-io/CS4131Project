package com.example.cs4131projecteddenchew.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Tag {
    var id: Long? = null
    var name: String? = null

    constructor(id_: Long?, name_: String?) {
        this.id = id_
        this.name = name_
    }
}