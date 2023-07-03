package com.mad.androidtraining.test

fun main(args:Array<String>) {

    var r = arrayOf(51,512,232,32,1516,45,6)

    println(r[0])
    println(r[3])
    println(r[4])
    println(r[1])
    println(r[2])

    println("------------------------------------")

    r.forEach {
        println("foreach $it")
    }


    println("------------------------------------")
    var a = listOf<Int>(5,7,4,3,2,5,4,6,7)

    println(a.get(3))
    println(a[0])
    println(a.get(0))
    println(a.indexOf(4))
    println(a.lastIndexOf(4))

    println("------------------------------------")

    var arrayList = arrayListOf<String>("hello","world","space","foo","bar")

    println(arrayList.get(0))
    println(arrayList.get(4))
    println(arrayList.size)
    println(arrayList.isEmpty())

    println("------------------------------------")

    var mixarray = ArrayList<Any>()
    mixarray.add(5)
    mixarray.add("gg")
    mixarray.add(5.555)
    mixarray.add(true)


    println(mixarray.get(0))
    println(mixarray.get(1))

    println("------------------------------------")

    var set = setOf<Any>("Kotlin",2.5,true,56,false,"Kotlin")
    println(set.size)
    println(set.last())





}