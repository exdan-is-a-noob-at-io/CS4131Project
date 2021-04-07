package com.example.cs4131projecteddenchew.model

import android.util.Log

class User {
    var id: String? = null
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

    var userSafe:UserSafe? = null


    companion object Test{
        var aes = AES()
        var key = "Praeclarum"
        public fun encryptVal(value:String?):String?{
            return aes.encrypt(value, key)
        }
    }

    //todo order this as per firebase

    constructor(){

    }

    constructor(bio: String?, email: String?, exp: Long?, id: String?, name: String?, profileSrc: String?, pw: String?, friends: List<String>) {
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

    constructor(bio: String?, email: String?, exp: Long?, id: String?, name: String?, profileSrc: String?, pw: String?) {
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

    constructor(id: String?, profileSrc: String?, bio: String?, exp: Long?, friends: List<String>, nameDecrypted: String?, emailDecrypted: String?, pwDecrypted: String?) {
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


    constructor(id: String?, name: String?, email: String?, pw: String?, encrypted: Boolean) {
        if (encrypted){
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
        }
        else{
            this.id = id
            this.profileSrc = ""
            this.bio = ""
            this.exp = 0
            this.friends = ArrayList<String>()
            this.nameDecrypted = name
            this.emailDecrypted = email
            this.pwDecrypted = pw


            this.name = aes.encrypt(nameDecrypted, key)
            this.email = aes.encrypt(emailDecrypted, key)
            this.pw = aes.encrypt(pwDecrypted, key)
        }

        userSafe = UserSafe(id, profileSrc, this.name, bio, exp, this.email, this.pw, friends)
    }



}