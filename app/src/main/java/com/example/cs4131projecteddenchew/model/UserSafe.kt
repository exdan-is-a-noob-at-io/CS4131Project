package com.example.cs4131projecteddenchew.model

import com.example.cs4131projecteddenchew.ui.viewmodel.adminViewModel

class UserSafe {
    var id: String?
    lateinit var profileSrc: String
    lateinit var name: String
    lateinit var bio: String
    var exp: Long? = 0
    lateinit var email: String
    lateinit var pw: String
    lateinit var friends: List<String>

    lateinit var questionsDone: List<Long>
    lateinit var questionsPosted: List<Long>

    constructor(id: String?, profileSrc: String?, name: String?, bio: String?, exp: Long?, email: String?,
                pw: String?, friends: List<String>?, questionsDone: List<Long>?, questionsPosted: List<Long>?) {
        this.id = id
        if (profileSrc != null) {
            this.profileSrc = profileSrc
        }
        if (name != null) {
            this.name = name
        }
        if (bio != null) {
            this.bio = bio
        }
        this.exp = exp
        if (email != null) {
            this.email = email
        }
        if (pw != null) {
            this.pw = pw
        }
        if (friends != null) {
            this.friends = friends
        }
        if (questionsDone != null) {
            this.questionsDone = questionsDone
        }
        if (questionsPosted != null) {
            this.questionsPosted = questionsPosted
        }
    }

    override fun toString(): String {
        return "UserSafe(id=$id, profileSrc='$profileSrc', name='$name', bio='$bio', exp=$exp, email='$email', pw='$pw', friends=$friends)"
    }


}