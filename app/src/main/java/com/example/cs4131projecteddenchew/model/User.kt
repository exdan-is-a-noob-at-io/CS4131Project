package com.example.cs4131projecteddenchew.model

class User {
    var id: Long? = null
    var profileSrc: String? = null
    var name: String? = null
    var bio: String? = null
    var exp: Long? = null
    var email: String? = null
    var pw: String? = null
    var friends: List<String>? = null

    //name, email, pw encrypted
    var nameEncrypted: String? = null
    var emailEncrypted: String? = null
    var pwEncrypted: String? = null

    var aes = AES()
    var key = "Praeclarum"

    var userSafe:UserSafe? = null

    constructor(id: Long?, profileSrc: String?, name: String?, bio: String?, exp: Long?, email: String?, pw: String?, friends: List<String>) {
        this.id = id
        this.profileSrc = profileSrc
        this.name = name
        this.bio = bio
        this.exp = exp
        this.email = email
        this.pw = pw
        this.friends = friends
        nameEncrypted = aes.decrypt(name, key)
        emailEncrypted = aes.decrypt(email, key)
        pwEncrypted = aes.decrypt(pw, key)

        userSafe = UserSafe(id, profileSrc, name, bio, exp, email, pw, friends)
    }

    constructor(id: Long?, profileSrc: String?, bio: String?, exp: Long?, friends: List<String>, nameEncrypted: String?, emailEncrypted: String?, pwEncrypted: String?) {
        this.id = id
        this.profileSrc = profileSrc
        this.bio = bio
        this.exp = exp
        this.friends = friends
        this.nameEncrypted = nameEncrypted
        this.emailEncrypted = emailEncrypted
        this.pwEncrypted = pwEncrypted

        name = aes.encrypt(nameEncrypted, key)
        email = aes.encrypt(emailEncrypted, key)
        pw = aes.encrypt(pwEncrypted, key)

        userSafe = UserSafe(id, profileSrc, name, bio, exp, email, pw, friends)
    }


}