package com.mad.androidtraining.test

fun main(a:Array<String>) {
    println("-----------------------------------")

    var age = mapOf<String,Int>("hello" to 20, "world" to 50)

    println(age["hello"])
    println(age["world"])

    println("-----------------------------------")

    var mutableage = mutableMapOf<String,Int>("hello" to 20, "world" to 50)

    println(mutableage["hello"])
    println(mutableage["world"])
    mutableage.put("space", 20)
    println(mutableage["space"])
    println(mutableage.get("space"))


    println("-----------------------------------")


}