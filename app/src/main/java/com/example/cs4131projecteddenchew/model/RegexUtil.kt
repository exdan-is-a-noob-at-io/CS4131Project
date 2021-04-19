package com.example.cs4131projecteddenchew.model

object RegexUtil {
    val nameRegex = Regex("\\w{3,}")
    val emailRegex = Regex(".+@.+")

    fun checkName(NRIC: String) = NRIC.matches(nameRegex)
    fun checkEmail(NRIC: String) = NRIC.matches(emailRegex)


    fun addBackSlash(string:String):String{
        var out = string

        out.replace("\\", "\\\\")
        return out
    }
}