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
    var questionsDone: List<Long>? = null
    var questionsPosted: List<Long>? = null

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

        public fun decryptVal(value:String?):String?{
            return aes.decrypt(value, key)
        }
    }

    constructor(){

    }

    constructor(bio: String?, email: String?, exp: Long?, id: String?, name: String?, profileSrc: String?,
                pw: String?, friends: List<String>, questionsDone: List<Long>?, questionsPosted: List<Long>?) {
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

        this.questionsDone = questionsDone
        this.questionsPosted = questionsPosted

        userSafe = UserSafe(id, profileSrc, name, bio, exp, email, pw, friends, questionsDone, questionsPosted)
    }

    constructor(bio: String?, email: String?, exp: Long?, id: String?, name: String?, profileSrc: String?,
                pw: String?, friends: List<String>) {
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

        this.questionsDone = ArrayList<Long>()
        this.questionsPosted = ArrayList<Long>()

        userSafe = UserSafe(id, profileSrc, name, bio, exp, email, pw, friends, questionsDone, questionsPosted)
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

        this.questionsDone = ArrayList<Long>()
        this.questionsPosted = ArrayList<Long>()

        userSafe = UserSafe(id, profileSrc, name, bio, exp, email, pw, friends, questionsDone, questionsPosted)
    }

    constructor(id: String?, profileSrc: String?, bio: String?, exp: Long?, friends: List<String>,
                nameDecrypted: String?, emailDecrypted: String?, pwDecrypted: String?) {
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

        this.questionsDone = ArrayList<Long>()
        this.questionsPosted = ArrayList<Long>()

        userSafe = UserSafe(id, profileSrc, name, bio, exp, email, pw, friends, questionsDone, questionsPosted)
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

        this.questionsDone = ArrayList<Long>()
        this.questionsPosted = ArrayList<Long>()

        userSafe = UserSafe(id, profileSrc, this.name, bio, exp, this.email, this.pw, friends, questionsDone, questionsPosted)
    }



}