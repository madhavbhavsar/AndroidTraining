package com.mad.androidtraining.test

fun main() {

    var myCar = Cars()

    myCar.name = "Hello"
    myCar.model = 2022
    println("${myCar.name} && ${myCar.model} && ${myCar.engine}")


    var myCar2 = Cars()

    myCar2.name = "World"
    myCar2.model = 2001
    println("${myCar2.name} && ${myCar2.model} && ${myCar2.engine}")


    var myCar3 = Cars()

    myCar3.name = "Space"
    myCar3.model = 2000
    myCar3.engine = 5
    println("${myCar3.name} && ${myCar3.model} && ${myCar3.engine}")



    //var myCar4 = Cars("Foo",8,1992)

   // println("${myCar4.name} && ${myCar4.model} && ${myCar4.engine}")
    println("-------------------------------------")



}