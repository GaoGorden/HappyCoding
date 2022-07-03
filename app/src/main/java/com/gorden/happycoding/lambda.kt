package com.gorden.happycoding


// 实现的效果相同
// 第一种无法传参
val method1: (String) -> Unit = { str: String -> println("method1:${str}") }
fun method(): (String) -> Unit = {str:String -> println("method:${str}") }
fun method2(str:String){
    println("method2:${str}")
}


fun main() {
    println(method1)
    println(method())
    println(method2("你好").javaClass.name)

    method1
    method()
    method2("nihao")

    invoke(method1)
    invoke(method())
}

fun invoke(action : (String) ->Unit){
    action("nihaoaaa")
}