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

    //name, email, pw Decrypted
    var nameDecrypted: String? = null
    var emailDecrypted: String? = null
    var pwDecrypted: String? = null

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
        nameDecrypted = aes.decrypt(name, key)
        emailDecrypted = aes.decrypt(email, key)
        pwDecrypted = aes.decrypt(pw, key)

        userSafe = UserSafe(id, profileSrc, name, bio, exp, email, pw, friends)
    }

    constructor(id: Long?, profileSrc: String?, bio: String?, exp: Long?, friends: List<String>, nameDecrypted: String?, emailDecrypted: String?, pwDecrypted: String?) {
        this.id = id
        this.profileSrc = profileSrc
        this.bio = bio
        this.exp = exp
        this.friends = friends
        this.nameDecrypted = nameDecrypted
        this.emailDecrypted = emailDecrypted
        this.pwDecrypted = pwDecrypted

        name = aes.encrypt(nameDecrypted, key)
        email = aes.encrypt(emailDecrypted, key)
        pw = aes.encrypt(pwDecrypted, key)

        userSafe = UserSafe(id, profileSrc, name, bio, exp, email, pw, friends)
    }


    constructor(id: Long?, name: String?, email: String?, pw: String?) {
        this.id = id
        this.profileSrc = ""
        this.name = name
        this.bio = ""
        this.exp = 0
        this.email = email
        this.pw = pw
        this.friends = ArrayList<String>()
        nameDecrypted = aes.decrypt(name, key)
        emailDecrypted = aes.decrypt(email, key)
        pwDecrypted = aes.decrypt(pw, key)

        userSafe = UserSafe(id, profileSrc, name, bio, exp, email, pw, friends)
    }



}