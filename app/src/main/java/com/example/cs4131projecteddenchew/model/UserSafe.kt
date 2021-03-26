package com.example.cs4131projecteddenchew.model

class UserSafe {
    var id: Long?
    lateinit var profileSrc: String
    lateinit var name: String
    lateinit var bio: String
    var exp: Long?
    lateinit var email: String
    lateinit var pw: String
    lateinit var friends: List<String>

    constructor(id: Long?, profileSrc: String?, name: String?, bio: String?, exp: Long?, email: String?, pw: String?, friends: List<String>?) {
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
    }
}