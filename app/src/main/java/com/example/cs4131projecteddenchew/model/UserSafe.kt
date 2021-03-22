package com.example.cs4131projecteddenchew.model

class UserSafe {
    var id: Long? = null
    var profileSrc: String? = null
    var name: String? = null
    var bio: String? = null
    var exp: Long? = null
    var email: String? = null
    var pw: String? = null
    var friends: List<String>? = null

    constructor(id: Long?, profileSrc: String?, name: String?, bio: String?, exp: Long?, email: String?, pw: String?, friends: List<String>?) {
        this.id = id
        this.profileSrc = profileSrc
        this.name = name
        this.bio = bio
        this.exp = exp
        this.email = email
        this.pw = pw
        this.friends = friends
    }
}