package com.example.jokeapp

fun main() {
    val arr = test()
    println(arr)
}

fun test(): ArrayList<String> {
    val string: ArrayList<String> = ArrayList()
    string.add("cat")
    string.add("dog")
    string.add("monkey")
    string.add("elephant")
    string.add("yguguygg")
    string.add("i")
    string.add("hi")
    string.add("hb")
    string.add("h")
    string.add("hanki")

    for (i in 1..100) {
        string.add(i.toString())
    }
    return string

}

//    val string2 = string.filter { it != "dog" }.toTypedArray()

//    println(string2.joinToString())

//    println(string.filter { it.length > 4 })

//    string.remove("yguguygg")

//    val sizee = string.size

//    val count = string.contains("h")

//    val count = (string.size.div(2)-1)
//    val ring = string[count]

//    val total : Array<String> = arrayOf("Volvo", "BMW", "Ford", "Mazda")
//    for (i in total){
//        println("car = $i")
//    }

//    var B = 0
//    for( B in 0..100 ){
//        println(B)
//    }

//    var sum = 0
//    var i = 100
//    while (i != 0){
//        sum += i
//    ++i
//        }
//    print("sun = $sum ")


//    println(ring)


//}