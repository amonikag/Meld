package com.realllydan.meld.data

class Dice{
    fun rand(): Int {
        var a=0
        for (i in 1..5) {
            (a*10+(1..6).random()).also { a = it }
        }
        return a

    }

}
fun main(){
    val d = Dice()
    println(d.rand())
}