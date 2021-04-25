package com.example.cs4131projecteddenchew.model

import java.util.*

object GamificationUtil {
    val levels = ArrayList<Long>(Arrays.asList(375, 500, 625, 725, 850,
        950, 1075, 1175, 1300, 1425, 1525, 1650, 1775, 1875, 1875, 2000,
        2375, 2500, 2625, 2775, 2825, 3425, 3725, 4000, 4300, 4575, 4975,
        5150, 5450, 5725, 6025, 6300, 6600, 6900, 7175,7475, 7750, 8050,
        8325, 8625, 10550, 11525, 12475, 13450, 14400, 15350, 16325,
        17275, 18250, 19200, 26400, 28800, 31200, 33600, 36000, 232350,
        258950, 285750, 312825, 340125
    ))

    fun getLevel(exp:Long):Long{
        var out:Long = 0
        var input = exp
        while (input >= 0){
            input -= levels[out.toInt()]
            out+=1
        }
        return out
    }

    fun getExp(exp:Long):Long{
        var out:Long = 1
        var input = exp
        while (input >= 0){
            if (input < levels[(out-1).toInt()]){
                return input
            }
            input -= levels[(out-1).toInt()]
            out+=1
        }
        return -1
    }

    fun getLevelExp(exp:Long):Long{
        var out = 1
        var input = exp
        while (input >= 0){
            input -= levels[out-1]
            out+=1
        }
        return levels[out-1]
    }
}